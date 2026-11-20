# Phase 1: Core Engine, Cleanup & Safety Fixes

**Goal:** Stabilize the calculation core, fix all known crafting/leftover/overflow/data bugs, completely remove the deprecated custom-item feature and unused `Gson` dependency, align the build configuration, and establish unit test coverage before expanding the UI.

**Depends on:** None (starting point on `dev`).  
**Unlocks:** Phase 2 (`Materials | Steps | Tree` tabs and live "Have X" inventory recalculation).

---

## 1A. Build & Dependency Cleanup

### Files
- `gradle/libs.versions.toml`
- `app/build.gradle.kts`
- `app/proguard-rules.pro`

### Tasks
1. **Align SDK version:** Set `compileSdk = 35` in `app/build.gradle.kts` to match `targetSdk = 35`.
2. **Stabilize Navigation:** Upgrade `navigationCompose` in `gradle/libs.versions.toml` from `2.8.0-rc01` to the latest stable `2.8.x` release (e.g., `2.8.9` — verify resolution via Gradle sync/build).
3. **Remove Gson:**
   - Remove `gson` version/library entries from `gradle/libs.versions.toml` and `implementation(libs.gson)` from `app/build.gradle.kts`.
   - Remove `-keep class com.google.gson.** { *; }` and `-dontwarn com.google.gson.**` from `app/proguard-rules.pro`.
4. **Testing dependencies:** Add `kotlinx-coroutines-test` (`testImplementation`) for ViewModel `StateFlow` unit tests (and keep `room-runtime`/`room-ktx`/`ksp` ready or remove Room dependencies until Phase 3 — keeping the catalog entry in `libs.versions.toml` is fine while unused Gradle dependencies are removed cleanly if desired, or keep Room in `build.gradle.kts` if preferred; document choice cleanly in commit).

---

## 1B. Deprecate & Completely Remove Custom Items / Recipes

Removing the unused custom-item Room scaffolding now eliminates the v1 → v2 `AppDatabase` crash risk (because `AppDatabase` will not be opened in v2.x until Phase 3 re-introduces it at `version = 3` with explicit `Migration(1, 3)` and `Migration(2, 3)`).

### Files to Delete
- `app/src/main/java/com/fracta7/crafter/data/local/db/AppDatabase.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/Converter.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/dao/CategoryDao.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/dao/ItemDao.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/dao/RecipeDao.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/dao/RecipeTypeDao.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/entity/CategoryEntity.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/entity/ItemEntity.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/entity/RecipeEntity.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/entity/RecipeTypeEntity.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/mapper/CategoryMapper.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/mapper/ItemMapper.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/mapper/RecipeMapper.kt`
- `app/src/main/java/com/fracta7/crafter/data/local/db/mapper/RecipeTypeMapper.kt`

### Files to Edit
- `app/src/main/java/com/fracta7/crafter/di/AppModule.kt` — remove `provideAppDb` (keep module shell or delete `AppModule.kt` until Phase 3 adds Room + DataStore providers).
- `app/src/main/java/com/fracta7/crafter/data/repository/AppRepositoryImpl.kt` — remove `AppDatabase` constructor dependency, DAOs, and the `CoroutineScope(Dispatchers.IO + job)` init block that mutated non-thread-safe lists/maps asynchronously.
- `app/src/main/java/com/fracta7/crafter/ui/navigation/Screens.kt` — remove `Route.AddCustomItem`, `Route.AddCustomRecipe`, and `Route.AddCustomRecipeType`.
- `app/src/main/java/com/fracta7/crafter/domain/model/Recipe.kt`, `RecipeType.kt`, `Category.kt` — remove `val custom: Boolean = false` (already removed from `Item.kt`).
- `README.md` — remove `"Adding custom items and recipes"` from Features.

---

## 1C. Quick Data & Crash Safety Fixes

### Files & Changes
1. **`app/src/main/java/com/fracta7/crafter/ui/elements/ItemAddDialog.kt`:**
   - Replace `input.toInt()` (L82) with `val parsed = it.toIntOrNull()`, validate `parsed != null && parsed in 1..MAX_ITEM_AMOUNT` (e.g., `999_999`), and display a clear error message when out of range so typing 10+ digits never throws `NumberFormatException`.
2. **`app/src/main/java/com/fracta7/crafter/data/local/SpecialRecipes.kt`:**
   - Fix `oxidized_copper_grate` (L160–L167): change requirement to `mapOf("weathered_copper_grate" to 1)` with `amount = 1` (or keep stonecutting separate in `RecipesInit.kt` — verify `RecipesInit.kt` for stonecutting equivalents).
   - Fix `waxed_weathered_chiseled_copper` (L534–L542): change `"weathered_cut_copper_slab" to 1` → `"weathered_chiseled_copper" to 1`.
   - Fix `waxed_oxidized_chiseled_copper` (L543–L551): change `"oxidized_cut_copper_slab" to 1` → `"oxidized_chiseled_copper" to 1`.
   - Fix `waxed_exposed_copper_door` (L624–L632): change `"exposed_copper_grate" to 1` → `"exposed_copper_door" to 1`.
3. **`app/src/main/java/com/fracta7/crafter/data/local/RecipeTypesInit.kt`:**
   - Fix L12 display name `"Campfire_cooking"` → `"Campfire Cooking"`.
4. **`app/src/test/java/com/fracta7/crafter/DataValidation.kt`:**
   - Add `assertTrue("Infinite loop detected in items: $unsafeItems", unsafeItems.isEmpty())` in `validateItemNonInfinity()` (L66–L81) so cycles actually fail the test suite.

---

## 1D. Pure Demand-Aggregated Crafting Solver

Replace the two-pass leftover logic in `ItemDecompose.kt` with a clean, deterministic, cycle-safe solver.

### New / Updated Domain Types (`domain/usecases/CraftingSolver.kt`)

```kotlin
data class CraftingStep(
    val itemId: ItemID,
    val recipe: Recipe,
    val totalRequired: Int,
    val usedFromOwned: Int,
    val craftCount: Int,      // number of recipe executions
    val totalProduced: Int,
    val leftover: Int,
    val depth: Int            // 0 = leaf craft, higher = depends on lower depths
)

data class CraftingNode(
    val itemId: ItemID,
    val requiredAmount: Int,
    val usedFromOwned: Int,
    val selectedRecipeIndex: Int,
    val selectedRecipe: Recipe?,
    val availableRecipeCount: Int,
    val craftCount: Int,
    val leftover: Int,
    val children: List<CraftingNode>
)

data class CraftingPlan(
    val rawMaterials: Map<ItemID, Int>,
    val leftovers: Map<ItemID, Int>,
    val steps: List<CraftingStep>,     // sorted bottom-up (ascending depth)
    val tree: List<CraftingNode>
)
```

### Algorithm Design
1. **Inputs:**
   - `targets: Map<ItemID, Int>`
   - `selectedRecipes: Map<ItemID, Int> = emptyMap()` (per-`ItemID` override index into `sortRecipesByEfficiency(...)`, clamped to valid bounds)
   - `ownedInventory: Map<ItemID, Int> = emptyMap()`
   - `recipeRegistry: RecipeRegistry`
   - `itemRegistry: ItemRegistry`
2. **DAG & Cycle Guard:**
   - Resolve the active recipe for any decomposable `itemId` using `selectedRecipes[itemId] ?: 0` (falling back safely if `recipes.isEmpty()` — treat as raw material instead of crashing with `IndexOutOfBoundsException`).
   - Maintain a `visiting: Set<ItemID>` path set during traversal/topological ordering; if an item is already on the current recursion path (cyclic recipe such as `iron_block -> 9 iron_ingot -> 1 iron_block` or trim duplication `template + diamond -> 2 template`), either break the cycle back to raw or pick a non-cyclic recipe.
3. **Demand Aggregation (Shared Pool):**
   - Walk targets in **topological order** (from highest-level products down to raw materials, or build the dependency DAG first and process in reverse topological order: outputs before their ingredients):
     1. Start with `pool = ownedInventory.toMutableMap()` (available owned/surplus counts) and `pendingDemand = targets.toMutableMap()`.
     2. Process each item in **top-down topological order** (parents before children) so every consumer's demand for intermediate item `X` is **summed into one total** before deciding how many times to craft `X`:
        - Subtract `pool[X]` (owned inventory) from `pendingDemand[X]`.
        - If remaining need `> 0` and `X` has an active recipe:
          - Compute `crafts = resourceAmount(recipe.amount, remainingNeed)`
          - Compute `produced = crafts * recipe.amount`
          - Compute `leftover = produced - remainingNeed` (always `>= 0`; never inserts `0` as a fake leftover!)
          - Record `CraftingStep` and add `reqQty * crafts` to `pendingDemand[reqItem]` for each requirement.
        - Else if remaining need `> 0` (raw / non-decomposable item):
          - Add `remainingNeed` to `rawMaterials[X]`.
   - Why top-down topological aggregation?
     - Example: Recipe A needs `3` planks, Recipe B needs `3` planks (total `6` planks). Instead of crafting `4+4=8` planks separately, the solver aggregates demand (`6` planks) → crafts `2` log-to-plank batches (`8` planks) → `2` logs raw, `2` planks leftover. No two-pass refund needed, and zero risk of the `amount % recipe.amount == 0 -> +1` bug!
   - Also construct `CraftingNode` tree cleanly for the `Tree` tab, carrying exact per-node recipe index & counts, and fix `ItemDecompose.kt` (either deprecate/delegate `decomposeItems(...)` to `solveCraftingPlan(...)` so existing tests keep compiling during transition, or update callers directly).

---

## 1E. ViewModel State Hoisting & Crafting Screen Fixes

### Files
- `app/src/main/java/com/fracta7/crafter/ui/crafting_screen/CraftingScreenViewModel.kt`
- `app/src/main/java/com/fracta7/crafter/ui/crafting_screen/CraftingScreen.kt`
- `app/src/main/java/com/fracta7/crafter/ui/elements/CraftingElement.kt`

### Changes
1. **Move calculation out of Composition:**
   - Remove `@SuppressLint("CoroutineCreationDuringComposition")` and `coroutineScope.launch { ... }` inside `CraftingScreen.kt` (L102–L110).
   - Introduce `CraftingUiState(targets, selectedRecipes, plan)` as a `StateFlow<CraftingUiState>` in `CraftingScreenViewModel` initialized via `SavedStateHandle` (reading `Route.Crafting` nav args) or `initTargets(items, amounts)`.
   - Expose `selectRecipe(itemId: ItemID, recipeIndex: Int)` on `CraftingScreenViewModel` which updates `selectedRecipes[itemId] = recipeIndex` and recomputes `plan` on `Dispatchers.Default` (or synchronously if fast, using `MutableStateFlow` + `combine`).
2. **Fix `CraftingElement.kt`:**
   - Remove local `var recipeIndex by remember { mutableIntStateOf(0) }` (L48); pass `selectedRecipes: Map<ItemID, Int>` and `onSelectRecipe: (ItemID, Int) -> Unit` down from `CraftingScreen`.
   - Fix L109 leftover formula:
     ```kotlin
     val crafts = resourceAmount(selectedRecipe.amount, amount)
     val leftOver = (crafts * selectedRecipe.amount) - amount
     ```
     (Previously used `% amount`, which computed `8 % 1 = 0` instead of `7`.)

---

## 1F. Unit Tests (Write Before/Alongside Solver Refactor)

### New Test Files (`app/src/test/java/com/fracta7/crafter/`)
1. **`CraftingSolverTest.kt`:**
   - Multi-item demand aggregation (e.g., two items each requiring `3 oak_planks` → `2` oak logs total, `2` planks leftover).
   - Exact-multiple leftovers (verifies the old `amount % recipe.amount == 0 -> +1` bug is gone).
   - Recipe override recalculation (switching `fuel` or `stone_slab` updates `rawMaterials`, `leftovers`, and `steps` across all consumers).
   - Owned inventory subtraction (partial and full ownership of raw and intermediate items).
   - Cycle safety (recipes like `diamond_block <-> 9 diamond` or armor trim duplication never throw `StackOverflowError`).
   - Bottom-up topological ordering verification (`steps` order guarantees every input is produced at a smaller depth or is raw).
2. **`StackCalculatorTest.kt`:**
   - `getStackAmount` for `0`, `< stackSize`, exact stack multiples, remainder, and `stackSize = 1` items.
3. **`SortRecipesTest.kt`:**
   - Efficiency ratio sorting and tie-breaking by `calculateTotalRequirements`.
4. **`CraftingScreenViewModelTest.kt`:**
   - Verifies `selectRecipe` updates `plan` in `StateFlow`.

---

## Verification Checklist
- [ ] `./gradlew testDebugUnitTest` passes with all new tests + tightened `DataValidation` passing.
- [ ] `./gradlew assembleDebug` succeeds with `compileSdk = 35`, stable `navigation-compose`, and no `Gson` or custom-item remnants.
- [ ] Manual check on emulator/device:
  - Entering `9999999999` in `AddItemDialog` shows validation error instead of crashing.
  - Switching a recipe (e.g. `stone_slab` from Crafting to Stonecutting, or `fuel` from Lava Bucket to Coal) updates **Raw Items** and **Left-overs** immediately and persists when scrolling the tree.
  - Crafting `1` `white_bed` or `8` `sticks` shows accurate leftover counts (no `% amount` zero bug).
