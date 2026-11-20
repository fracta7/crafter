# Phase 4: Data-Driven JSON Pipeline, 3×3 Grids & OTA Updates

**Goal:** Replace hardcoded 5,600-line Kotlin item/recipe lists with an automated Minecraft JSON generator pipeline, unlock first-class item tags, Tipped Arrows, visual 3×3 crafting grids & station diagrams, and optional GitHub-hosted over-the-air registry updates with Coil-cached remote icons.

**Depends on:** Phase 1 (`kotlinx.serialization`, clean solver), Phase 2 (`ItemInfoDialog` & `StepsTab`), Phase 3 (`UserPreferences` for fuel/wood/stone tags & `SettingsScreen` Registry section).  
**Unlocks:** Low-effort future Minecraft version updates without app store releases.

---

## 4A. Offline Minecraft Data Generator (`tools/mc-data-generator/`)

### Why Generate Instead of Hand-Editing
Hand-maintaining 1,400+ items and 1,600+ recipes in Kotlin files caused missing base ingredients (`charcoal` missing logs, dyed wool/carpets missing wool/carpet), makes MC updates tedious, and lacks grid layout shapes.

### Generator Inputs & Outputs
Create a standalone Python (or Kotlin) CLI tool under `tools/mc-data-generator/` that reads:
1. Extracted official Minecraft jar data (`data/minecraft/recipe/*.json`, `data/minecraft/tags/item/*.json`) + `en_us.json` localization names & item list.
2. A small curated `special_recipes.json` overlay for non-JSON-recipe mechanics (`oxidation`, `waxing`, `stripping`, `watering`, `fuel`, `brewing`, `tipped_arrows` if not expanded directly, plus custom `stackSize` & `categories` mappings).

And emits into `app/src/main/assets/registry/`:
- `manifest.json` — `schemaVersion`, `mcVersion`, `registryVersion` (monotonically increasing int), `generatedAt`, and SHA-256 checksums + byte sizes for every JSON file.
- `items.json` — id, display name, `stackSize`, `decomposable`, `tags` (UI categories).
- `recipes.json` — id, `result`, `amount`, `recipeType`, `ingredients` (supporting concrete `ItemID` or `#tag`), plus optional `grid` (`pattern: List<String>`, `key: Map<String, IngredientRef>`) / `stationSlots` (smelting input+fuel, brewing base+reagent+fuel, smithing template+base+addition).
- `item_tags.json` — resolved Minecraft item tag lists (`minecraft:planks`, `minecraft:logs_that_burn`, `minecraft:wool`, `minecraft:stone_crafting_materials`, etc.).
- `categories.json` & `recipe_types.json`.

### Tipped Arrows & Missing Ingredient Resolution
- **Tipped Arrows:** Generator systematically creates all tipped arrow recipes (`8 arrow + 1 <effect>_lingering_potion -> 8 <effect>_tipped_arrow`) and item entries, reusing existing potion metadata and icons/generated assets.
- **Missing tag ingredients:** Because recipes keep real `#tag` ingredients (e.g., `charcoal` = `#logs_that_burn` + `#fuel`; dyed wool = `#wool` + `<color>_dye`; beds = `3 #wool + 3 #planks`), all legacy missing-base-ingredient typos in `RecipesInit.kt` disappear automatically.

---

## 4B. App Registry Loader, Tag Resolution & Reactive Repository

### Domain Model Updates
- Introduce `IngredientRef`:
  ```kotlin
  @Serializable
  sealed interface IngredientRef {
      @Serializable @SerialName("item") data class Item(val id: ItemID) : IngredientRef
      @Serializable @SerialName("tag") data class Tag(val id: TagID) : IngredientRef
  }
  ```
- Extend `Recipe` with:
  - `requirements: List<IngredientRequirement>` (or `Map<IngredientRef, Int>`)
  - Optional `layout: RecipeLayout?` (`CraftingGrid(pattern, key)`, `Furnace(input)`, `Brewing(base, reagent)`, `Smithing(template, base, addition)`).
- **Dynamic Tag Resolution (`TagResolver`):**
  - Resolves `#tag` into a concrete `ItemID` using Phase 3 `UserPreferences` (`preferredWood`, `preferredStone`, `preferredFuel`, default `white_wool` for `#wool`, etc.), falling back deterministically to the first valid member in `item_tags.json`.
  - Runs seamlessly inside `CraftingSolver`, keeping `CraftingPlan` clean and automatically respecting user preferences!

### Registry Loader & Reactive Snapshot
- Replace hardcoded `ItemsInit.kt`, `RecipesInit.kt`, `SpecialRecipes.kt`, `TagsInit.kt`, `RecipeTypesInit.kt` once verified in tests.
- Implement `RegistryLoader`:
  1. On startup, check `filesDir/registry_cache/manifest.json` (from a prior OTA update).
  2. If cached `schemaVersion == SUPPORTED_SCHEMA_VERSION` and `registryVersion > bundledManifest.registryVersion`, load from `filesDir/registry_cache/`.
  3. Otherwise fall back cleanly to bundled `assets/registry/`.
- Update `AppRepository` to expose `registryState: StateFlow<RegistrySnapshot>` so when an OTA update installs at runtime, `MainActivityViewModel`, `CraftingScreenViewModel`, and `ProjectsViewModel` pick up new items/recipes without requiring an app restart.

---

## 4C. Visual 3×3 Crafting Grid & Station Preview UI

### Files
- `app/src/main/java/com/fracta7/crafter/ui/elements/RecipeGridView.kt`
- `app/src/main/java/com/fracta7/crafter/ui/elements/ItemInfoDialog.kt`
- `app/src/main/java/com/fracta7/crafter/ui/crafting_screen/components/StepsTab.kt`

### UI Design
Render compact, Minecraft-inspired visual slot diagrams depending on `Recipe.layout`:
1. **3×3 Crafting Table Grid** (shaped & shapeless): 3×3 slot boxes with item icons (and tag cycling/badge indicator when an input is a `#tag` like `#planks`), arrow → result + count badge.
2. **Furnace / Smoker / Blast Furnace / Campfire:** Input slot over fuel slot → output slot.
3. **Brewing Stand:** Reagent top slot + base potion bottom slots + blaze powder fuel indicator → result potion.
4. **Smithing Table:** Template + Base + Addition → Result.
5. **Stonecutter / Stripping / Oxidation / Waxing / Watering:** Source slot + station/tool badge → result.

Integrate `RecipeGridView` inside `ItemInfoDialog.kt` (`"How to craft"`) and as an expandable preview on each step card in `StepsTab`.

---

## 4D. Online Registry Updates (GitHub Manifest + Remote Icons)

### Files & Manifest Changes
- `app/src/main/AndroidManifest.xml` — add `<uses-permission android:name="android.permission.INTERNET" />`.
- `app/src/main/java/com/fracta7/crafter/data/remote/RegistryUpdateManager.kt`
- `app/src/main/java/com/fracta7/crafter/ui/elements/DrawItem.kt`
- `app/src/main/java/com/fracta7/crafter/ui/settings_screen/SettingsScreen.kt`

### Update Protocol (Safe & Offline-First)
1. **Manifest Fetch:** Fetch remote `manifest.json` from a configurable GitHub Raw / GitHub Releases URL (`HttpURLConnection` on `Dispatchers.IO` with strict timeouts — zero heavy networking dependencies needed).
2. **Compatibility & Version Gate:** Verify `remote.schemaVersion == SUPPORTED_SCHEMA_VERSION` and `remote.registryVersion > active.registryVersion`.
3. **Atomic Download & Verification:**
   - Download JSON files into `filesDir/registry_tmp/`.
   - Verify SHA-256 hash of every downloaded file against `manifest.json`.
   - Test-parse all JSON files into a `RegistrySnapshot` **before** activating.
   - Atomically replace `filesDir/registry_cache/` and emit the new `RegistrySnapshot` on `AppRepository.registryState`.
   - On any error, clean `registry_tmp/` and leave bundled/previous cache untouched.
4. **Remote Icon Fallback in `DrawItem.kt`:**
   - Primary source: `file:///android_asset/items/$itemID.png` (instant, 100% offline for all bundled items).
   - Fallback for newly introduced OTA items: use Coil `ImageRequest` pointing to `manifest.iconBaseUrl + "$itemID.png"` with Coil disk caching enabled, falling back to `R.drawable.rounded_cube_24` when offline.
5. **Settings UI Controls:**
   - Display active `mcVersion`, `registryVersion`, source (`Bundled` vs `Updated OTA`), and last-checked timestamp.
   - Buttons: **"Check for updates"** (shows up-to-date / downloading progress / updated to MC X.Y.Z!) and **"Reset to bundled data"**.

---

## Verification Checklist
- [ ] Run `tools/mc-data-generator/` and confirm zero missing requirements, zero missing recipe types, zero missing icons (`ValidateResources` / unit asset check).
- [ ] `RegistryIntegrityTest.kt` passes on bundled JSON assets:
  - Every `decomposable` item has `>= 1` valid recipe.
  - Every concrete `ItemID` and `#TagID` resolves to valid items.
  - Zero infinite cycles under `CraftingSolver`.
  - Shaped grids fit `<= 3x3` bounds and match recipe requirement quantities.
- [ ] Unit tests for `TagResolver` (respects `UserPreferences.preferredWood`, `preferredStone`, `preferredFuel`), `RegistryLoader` (cache vs bundled priority & corrupt cache fallback), and `RegistryUpdateManager` (SHA-256 mismatch rejection & schema version guard).
- [ ] `./gradlew testDebugUnitTest && ./gradlew assembleDebug` passes.
- [ ] Manual QA: view 3×3 grids in `ItemInfoDialog` and `StepsTab`, inspect tipped arrow crafting chains, and test OTA update flow (including offline/rollback behavior).
