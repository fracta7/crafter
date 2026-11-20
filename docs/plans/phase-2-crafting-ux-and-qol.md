# Phase 2: Crafting UX, Inventory Checklist & QoL

**Goal:** Turn the Crafting Screen into an active in-game companion with `Materials | Steps | Tree` tabs, live per-session "Have X" inventory tracking, bottom-up workstation steps, Shulker/Chest formatting, quick quantity editing, Mini-JEI reverse recipe lookup, fast pre-indexed catalog search, and unified Minecraft styling.

**Depends on:** Phase 1 (`CraftingPlan`, `CraftingScreenViewModel` `StateFlow`, per-`ItemID` recipe overrides).  
**Unlocks:** Phase 3 (persisting `ownedInventory` & `selectedRecipes` inside Saved Projects).

---

## 2A. Tabbed Crafting Screen + Progress Header

### Files
- `app/src/main/java/com/fracta7/crafter/ui/crafting_screen/CraftingScreen.kt`
- New components under `app/src/main/java/com/fracta7/crafter/ui/crafting_screen/components/` (or `ui/elements/`):
  - `MaterialsTab.kt`
  - `StepsTab.kt`
  - `TreeTab.kt`
  - `OwnedQuantityDialog.kt`

### Layout Design
1. **TopAppBar + Progress Bar:**
   - Keep TopAppBar with back navigation and quick reset action (clear checked/"Have" counts).
   - Below TopAppBar, render a compact summary card with a `LinearProgressIndicator`:
     - Shows overall completion (`gathered/needed` raw items + `completed/total` craft steps) and an optional **Summary Chip** (e.g., `"12 raw types • 7 steps • 4 leftovers"`).
2. **`PrimaryTabRow` with 3 Tabs:**
   - **`Materials`** — Raw Materials needed + Leftovers.
   - **`Steps`** — Bottom-up crafting order grouped by workstation.
   - **`Tree`** — Interactive recursive breakdown tree.
   - Use `HorizontalPager` (or state-backed tab switch preserving scroll position per tab).

---

## 2B. Existing Inventory ("Have X") & Gathering Checklist

### ViewModel State (`CraftingScreenViewModel.kt`)
- Add `ownedInventory: Map<ItemID, Int>` to `CraftingUiState` (all items start at `0` in Phase 2; seeded from saved project in Phase 3).
- Actions:
  - `setOwnedAmount(itemId: ItemID, amount: Int)` — sets exact owned count (`>= 0`) and re-runs `solveCraftingPlan(...)`.
  - `toggleItemComplete(itemId: ItemID, requiredTotal: Int)` — if currently `< requiredTotal`, sets owned to `requiredTotal`; otherwise resets owned to `0`.
  - `resetOwnedInventory()` — clears all owned overrides.
- *Important solver behavior:* Keep track of the **baseline** raw requirement (when `owned = 0`) or display `have / totalRequired` alongside `remainingToGather`, so checking off a raw item marks it as gathered (`done` styling with strikethrough/muted alpha and checked box) rather than silently vanishing before the user sees what they checked off!
  - Specifically:
    - If a user marks they have `32 / 64` Iron Ingots: item stays visible showing `Need 32 more (Have 32 / 64)`.
    - If a user checks off `64 / 64` Iron Ingots: item moves to a collapsible `"Collected"` sub-section or remains in place with a checked box and strikethrough, while dependent crafting steps know those ingots are already on hand!
    - If a user marks an **intermediate** step (e.g., `Pistons`) as already crafted (`Have X`), the solver subtracts those owned pistons and dynamically lowers the remaining raw material requirements (Iron, Redstone, Cobblestone, Planks) in `Materials`!

### Tab Details
1. **`MaterialsTab`:**
   - **Raw Materials list:** Each row shows item icon, name, formatted remaining amount (with stacks / Shulker info), `"Have X"` badge/chip when `> 0`, and a `Checkbox` (tap row/badge to edit exact "Have X" quantity; tap checkbox to mark 100% gathered).
   - **Leftovers card/section:** Shown below Raw Materials when `plan.leftovers.isNotEmpty()`, clearly separated so leftover outputs aren't confused with materials to gather.
2. **`StepsTab`:**
   - Uses `plan.steps` (already topologically sorted bottom-up: lowest `depth` first).
   - Optional filter/grouping toggle: **By Order** (depth sequence) vs. **By Workstation** (`Crafting`, `Smelting`, `Blasting`, `Smoking`, `Stonecutting`, `Brewing`, etc.).
   - Each step card displays:
     - Workstation icon (`RecipeType.item`) + method name
     - Input ingredients with exact quantities needed for this step
     - Output item + `craftCount` batches (and leftover note if any)
     - Alternate recipe swap button (if `recipes.size > 1`)
     - `Checkbox` / tap-to-set "Already have X" so checking off a step updates downstream needs.
3. **`TreeTab`:**
   - Uses `CraftingElement` backed by `plan.tree` and `ViewModel.selectRecipe(...)`.

---

## 2C. Shulker Box / Double Chest Units & Faster Quantity Input

### Files
- `app/src/main/java/com/fracta7/crafter/domain/usecases/StackCalculator.kt`
- `app/src/main/java/com/fracta7/crafter/domain/usecases/StackTextBuilder.kt`
- `app/src/main/java/com/fracta7/crafter/ui/elements/ItemAddDialog.kt`
- `app/src/main/java/com/fracta7/crafter/ui/main_activity/MainScreen.kt`

### Changes
1. **Container Stack Units:**
   - Add `getContainerBreakdown(amount: Int, stackSize: Int)` in `StackCalculator.kt`:
     - `1 Shulker Box = 27 * stackSize`
     - `1 Double Chest = 54 * stackSize` (optional toggle or shown for very large counts `>= 54 stacks`; Shulker Box shown when `>= 27 stacks`).
   - Update `getStackText(...)` in `StackTextBuilder.kt` with a `showShulkerUnits: Boolean = true` parameter so large material counts format cleanly (e.g., `3,456 (2 shulkers + 0 stacks)` or `1,792 (1 shulker, 1 stack)`).
2. **Quick-Increment Chips in `ItemAddDialog.kt`:**
   - Keep numeric text input and add horizontal quick-add chips (`+1`, `+16`, `+64`, `+1 stack`, `+1 shulker` when `stackSize > 1`; `+1`, `+9`, `+27` when `stackSize == 1` or adapt `+1 stack` to `item.stackSize`).
   - Optionally pre-fill existing amount or show `"Currently in list: N"` when item is already queued.
3. **Inline Queue Editing in Bottom Sheet (`MainScreen.kt`):**
   - In the `ModalBottomSheet` crafting queue, add `-` / `+` stepper buttons and tap-row-to-edit exact quantity (reusing `AddItemDialog` in edit mode) alongside the delete icon.

---

## 2D. Mini-JEI Reverse Lookup ("Used In")

### Files
- `app/src/main/java/com/fracta7/crafter/domain/model/RecipeRegistry.kt`
- `app/src/main/java/com/fracta7/crafter/ui/elements/ItemInfoDialog.kt`

### Changes
1. **Reverse Index in `RecipeRegistry.kt`:**
   - Build lazy/indexed lookup maps on registration (`recipesByResult: Map<ItemID, List<Recipe>>` and `recipesByIngredient: Map<ItemID, List<Recipe>>`) for $O(1)$ queries.
   - Add `getRecipesUsingIngredient(itemId: ItemID): List<Recipe>`.
2. **Interactive `ItemInfoDialog.kt`:**
   - Keep existing item metadata rows (`ID`, `Name`, `Stack size`, `Decomposable`, `Tags`).
   - Add two expandable/scrollable sections:
     - **"How to craft"** — lists all recipes producing this item (inputs → output + station icon).
     - **"Used to craft…"** — lists all recipes that require this item as an ingredient.
   - Tapping any ingredient or result item inside `ItemInfoDialog` navigates the dialog to that item (with a simple in-dialog back history stack) so players can browse the entire recipe graph offline.

---

## 2E. Catalog Search Performance & Theme / Typography Polish

### Files
- `app/src/main/java/com/fracta7/crafter/ui/main_activity/MainActivityViewModel.kt`
- `app/src/main/java/com/fracta7/crafter/ui/main_activity/MainScreen.kt`
- `app/src/main/java/com/fracta7/crafter/ui/main_activity/MainActivity.kt`
- `app/src/main/java/com/fracta7/crafter/ui/navigation/Navigation.kt`
- `app/src/main/java/com/fracta7/crafter/ui/theme/Color.kt`
- `app/src/main/java/com/fracta7/crafter/ui/theme/Type.kt`

### Changes
1. **Pre-Indexed Filtering in `MainActivityViewModel.kt`:**
   - Pre-group items by `TagID` once on init (`Map<TagID, List<Item>>`).
   - Hold `searchQuery: StateFlow<String>` and `selectedCategories: StateFlow<Set<TagID>>` in the ViewModel and emit a pre-filtered `CatalogSection` list via `combine(...)` on `Dispatchers.Default`.
   - Eliminates the 22 full 1,406-item scans per recomposition in `MainScreen.kt` (L181–L209).
   - Add a quick `"All"` / `"Clear"` chip at the start of the `FilterChip` row (or tap-only-this-category long-press shortcut) so filtering down to 1 category doesn't require unchecking 10 chips.
2. **Single `CrafterTheme` & Minecraft Palette:**
   - Keep `CrafterTheme` at the top level in `MainActivity.kt` only; remove duplicate `CrafterTheme` wrappers from `Navigation.kt` (which had `dynamicColor = false`), `MainScreen.kt`, and `CraftingScreen.kt`.
   - Replace the Android Studio template `Purple80 / PurpleGrey80 / Pink80` fallback scheme in `Color.kt` with a Minecraft-inspired palette (emerald/grass green primary, terracotta/copper secondary, stone/deepslate surface tones).
   - Apply `AppFontFamily` (`mojangles`) across all `Typography` slots (`display*`, `headline*`, `title*`, `body*`, `label*`) in `Type.kt`.

---

## Verification Checklist
- [ ] Unit tests for `StackCalculator` (shulker & double chest math), `RecipeRegistry.getRecipesUsingIngredient`, `MainActivityViewModel` filtering, and `CraftingScreenViewModel` `ownedInventory` subtraction.
- [ ] `./gradlew testDebugUnitTest && ./gradlew assembleDebug` passes.
- [ ] Manual QA on device/emulator:
  - Toggle between `Materials`, `Steps`, and `Tree`; check off raw materials and enter partial `"Have X"` amounts; verify progress bar and remaining counts update accurately.
  - Long-press `redstone` → open `ItemInfoDialog` → verify `"Used to craft…"` lists Piston, Observer, Repeater, etc., and tapping one opens that item's info.
  - Edit item quantities directly inside the queue bottom sheet and verify `+1 stack` / `+1 shulker` chips in `AddItemDialog`.
  - Verify instant search typing with no frame drops and consistent theme/Mojangles typography across screens.
