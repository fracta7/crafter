# Phase 3: Saved Projects, Litematica Import, Navigation & Settings

**Goal:** Add persistent multi-project support (plus active draft auto-save), Material 3 bottom navigation (`Catalog | Projects | Settings`), plain-text/Markdown export, Litematica material list import, and a DataStore-backed Settings screen including material/fuel preferences.

**Depends on:** Phase 1 (Room legacy custom files removed, `kotlinx.serialization` standardized) and Phase 2 (`CraftingScreenViewModel` state shape, theme/font cleanup).  
**Unlocks:** Phase 4 (Data-driven tag resolution uses Settings preferences; Settings hosts OTA Registry Update controls).

---

## 3A. Material 3 Bottom Navigation (`Catalog | Projects | Settings`)

### Files
- `app/src/main/java/com/fracta7/crafter/ui/navigation/Screens.kt`
- `app/src/main/java/com/fracta7/crafter/ui/navigation/Navigation.kt`
- `app/src/main/java/com/fracta7/crafter/ui/main_activity/MainScreen.kt`

### Route Structure
```kotlin
@Serializable
sealed class Route {
    @Serializable data object Catalog : Route()
    @Serializable data object Projects : Route()
    @Serializable data object Settings : Route()
    @Serializable data class Crafting(val projectId: Long) : Route()
}
```
*(Or keep a lightweight `Route.Crafting` variant supporting either `projectId: Long` for saved/draft projects, since every active session is backed by a draft or saved project row in Room.)*

### Navigation UX
- Top-level `Scaffold` wraps `NavHost` and renders a Material 3 `NavigationBar` (`Catalog`, `Projects`, `Settings`) **only** when the current destination is one of the 3 top-level tabs (hidden on `Crafting`).
- **`Catalog` tab** (`MainScreen`): keeps the search bar, category filter chips, queue bottom sheet trigger, and floating `Craft!` action button / active-queue pill. Tapping `Craft!` saves/updates the draft project in Room and navigates to `Route.Crafting(projectId = DRAFT_ID)` (or offers `"Save as Project"` directly from `CraftingScreen` TopAppBar).

---

## 3B. Room Database v3 (Saved Projects & Auto-Saved Draft)

### Gradle & Schema Export (`app/build.gradle.kts`)
- Configure KSP Room schema directory:
  ```kotlin
  ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
  }
  ```
- Re-add Room `AppDatabase` (`version = 3`, `exportSchema = true`) for database name `"app-database"` in `di/AppModule.kt`.

### Entity & Schema (`data/local/db/`)
Using a single-table JSON-backed entity serialized with `kotlinx.serialization` keeps schema simple, atomic, and free of multi-table join drift:

```kotlin
@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val isDraft: Boolean = false,
    val targetsJson: String,          // Map<ItemID, Int>
    val ownedInventoryJson: String,   // Map<ItemID, Int>
    val selectedRecipesJson: String,  // Map<ItemID, Int>
    val createdAt: Long,
    val updatedAt: Long
)
```

### Explicit Migrations (v1 → v3 & v2 → v3)
Users upgrading from release `2.5` (DB v1) or `2.6` (DB v2) already have `"app-database"` with old `items`, `recipes`, `categories`, `recipe_types` tables. Since Custom Items/Recipes are deprecated:
- **`MIGRATION_1_3`** and **`MIGRATION_2_3`**:
  1. `DROP TABLE IF EXISTS items`
  2. `DROP TABLE IF EXISTS recipes`
  3. `DROP TABLE IF EXISTS categories`
  4. `DROP TABLE IF EXISTS recipe_types`
  5. `CREATE TABLE IF NOT EXISTS projects (...)` matching `ProjectEntity` exact SQLite schema.
- Register `.addMigrations(MIGRATION_1_3, MIGRATION_2_3)` in `AppModule.provideAppDb(...)`.
- Add `room-testing` (`androidTestImplementation`) and write `MigrationTest.kt` verifying both v1 → v3 and v2 → v3 upgrades pass without throwing.

### Repository & Draft Auto-Save
- `ProjectDao` (`Flow<List<ProjectEntity>>` for saved projects + `getById`, `getDraft`, `upsert`, `deleteById`).
- `ProjectRepository` wrapping serialization/deserialization cleanly.
- `MainActivityViewModel` observes the draft project (loading queued items back on app launch) and debounces auto-saves whenever items are added/edited/cleared.
- `CraftingScreenViewModel` loads the project by `projectId` and persists `ownedInventory` and `selectedRecipes` updates immediately so progress survives app restarts.

---

## 3C. Projects Screen, Export & Litematica Import

### Files
- `app/src/main/java/com/fracta7/crafter/ui/projects_screen/ProjectsScreen.kt`
- `app/src/main/java/com/fracta7/crafter/ui/projects_screen/ProjectsViewModel.kt`
- `app/src/main/java/com/fracta7/crafter/domain/usecases/LitematicaParser.kt`
- `app/src/main/java/com/fracta7/crafter/domain/usecases/MaterialListExporter.kt`

### Features
1. **Projects List (`ProjectsScreen`):**
   - Displays all saved projects (`isDraft == false`) sorted by `updatedAt` descending, plus a banner/card if an unsaved active draft has items.
   - Each card shows project name, target item icon stack preview, total target count, completion percentage (computed from `ownedInventory` vs `plan`), and actions: **Open**, **Rename**, **Duplicate**, **Export**, **Delete**.
   - Also allow `"Save as Project"` from the `Catalog` queue bottom sheet and `CraftingScreen` TopAppBar (promoting or cloning the active draft).
2. **Text / Markdown Export (`MaterialListExporter`):**
   - Formats target items, remaining raw materials (with stack/shulker counts), and optional checklist boxes (`- [ ] Item x64`) as Plain Text or Markdown.
   - Trigger standard Android share sheet (`Intent.ACTION_SEND`) or copy to clipboard.
3. **Litematica Material List Import (`LitematicaParser`):**
   - Open `.txt` / `.csv` files via `ActivityResultContracts.OpenDocument`.
   - Support both standard Litematica export formats:
     - **ASCII table `.txt`** (`| Item | Total | Missing | Available |` pipe-delimited rows, ignoring border/header/total lines).
     - **`.csv`** (`"Item","Total","Missing","Available"` header + quoted rows).
   - Match each row's item name against `ItemRegistry` (case-insensitive exact `name` match, normalized snake_case `id` fallback, and common alias normalization).
   - Present an **Import Preview Dialog**: shows matched items + quantities, lets the user toggle `Total` vs `Missing` column, highlights any unmatched entries (so nothing fails silently), and creates a new saved project directly.

---

## 3D. Settings Screen (Jetpack DataStore Preferences)

### Dependencies & Files
- `gradle/libs.versions.toml` & `app/build.gradle.kts` — add `androidx.datastore:datastore-preferences`.
- `app/src/main/java/com/fracta7/crafter/data/preferences/UserPreferences.kt`
- `app/src/main/java/com/fracta7/crafter/data/preferences/UserPreferencesRepository.kt`
- `app/src/main/java/com/fracta7/crafter/ui/settings_screen/SettingsScreen.kt`
- `app/src/main/java/com/fracta7/crafter/ui/settings_screen/SettingsViewModel.kt`

### Settings & Behavior Wiring
| Preference | Options / Type | Where Applied |
| --- | --- | --- |
| **Preferred Fuel** | `coal`, `charcoal`, `blaze_rod`, `dried_kelp_block`, `lava_bucket` (default `coal`) | `CraftingSolver` / `SortRecipes` puts the preferred fuel recipe first for `"fuel"` (eliminating the current `lava_bucket` 100/1 bias!). |
| **Preferred Wood** | `oak`, `spruce`, `birch`, `jungle`, `acacia`, `dark_oak`, `mangrove`, `cherry`, `bamboo`, `crimson`, `warped`, `pale_oak` | Ready for Phase 4 tag resolution (and optional default wood substitution in Phase 3 where applicable). |
| **Preferred Stone** | `cobblestone`, `stone`, `blackstone`, `deepslate` | Ready for Phase 4 `#stone_crafting_materials` tag resolution. |
| **Prefer Stonecutter** | `Boolean` (default `true`) | `SortRecipes` / `CraftingSolver` prioritizes `stonecutting` over `crafting_shaped` when efficiency is equal or better. |
| **Theme Mode** | `SYSTEM`, `LIGHT`, `DARK` | `MainActivity` passes into `CrafterTheme(darkTheme = ...)`. |
| **Dynamic Color** | `Boolean` (default `true` on API 31+) | `MainActivity` passes into `CrafterTheme(dynamicColor = ...)`. |
| **Use Mojangles Font** | `Boolean` (default `true`) | `CrafterTheme` selects `AppTypography` (Mojangles) vs default Material typography. |
| **Show Shulker Box Units** | `Boolean` (default `true`) | Passed into `StackTextBuilder.getStackText(...)`. |
| **Keep Screen On While Crafting** | `Boolean` (default `false`) | `DisposableEffect` on `CraftingScreen` sets/clears `WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON`. |
| **Registry Data** *(section placeholder)* | Displays bundled MC version (`1.21.11`) | Hosts Phase 4 OTA update controls (`Check for updates`, `Last updated`, `Reset to bundled`). |

---

## Verification Checklist
- [ ] Unit tests for `LitematicaParser` (ASCII `.txt`, `.csv`, edge cases, unmatched items), `MaterialListExporter`, and `UserPreferences` sorting/fuel bias.
- [ ] Room `MigrationTest` (`androidTest`) verifies `MIGRATION_1_3` and `MIGRATION_2_3` cleanly migrate old `"app-database"` files into v3 `projects` schema.
- [ ] `./gradlew testDebugUnitTest && ./gradlew assembleDebug` passes.
- [ ] Manual QA on device/emulator:
  - Add items in `Catalog`, force-close app, reopen → active draft queue is still there.
  - Save queue as a named project, open from `Projects` tab, check off items in `CraftingScreen`, force-close, reopen → progress is preserved.
  - Import a real Litematica `.txt` and `.csv` export and verify preview + project creation.
  - Change Preferred Fuel to `coal` and toggle Dark Theme / Font / Dynamic Color in `Settings` → observe immediate effect.
