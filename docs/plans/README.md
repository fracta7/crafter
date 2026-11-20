# Crafter Implementation Plan (`dev`)

Master roadmap for evolving **Crafter** from v2.6 into a reliable, project-aware, data-driven Minecraft crafting companion.

---

## Locked-In Architecture Decisions

These choices were finalized before Phase 1 and govern all four phases:

| Area | Decision |
| --- | --- |
| **Git & Tracking Workflow** | Work phase-by-phase directly on the `dev` branch (branched from `master` at `298cf79`). Update task statuses in [TODO.md](../../TODO.md) (`TODO` → `IN PROCESS` → `IMPLEMENTED`) as work progresses. |
| **Custom Items / Recipes** | **Deprecated & completely removed** (Phase 1): delete legacy Room entities/DAOs/mappers/converters, unused navigation routes, `custom` flags, `Gson`, and README mentions. |
| **Build & Dependencies** | Align `compileSdk = 35` with `targetSdk = 35`, upgrade `navigation-compose` off `2.8.0-rc01` to stable, standardize on `kotlinx.serialization` (remove `Gson`), and use Jetpack DataStore (Preferences) for settings. |
| **Recipe Override Scope** | **Per `ItemID` across the entire crafting session** — switching e.g. Fuel to Coal or Slabs to Stonecutter updates every occurrence, Raw Items, Leftovers, and Steps consistently. |
| **Existing Inventory ("Have X")** | **Per-session / per-project** counts directly on the Crafting Screen (tap checkbox to mark gathered/crafted or enter exact owned amount, recalculating remaining needs live; persisted per project in Phase 3). |
| **Crafting Screen Layout** | Top progress header + Material 3 tabs: **`Materials`** (Raw Items + Have/Owned + Leftovers) \| **`Steps`** (bottom-up crafting order grouped by workstation) \| **`Tree`** (recursive breakdown). |
| **Top-Level Navigation** | Material 3 bottom `NavigationBar` with **`Catalog`** \| **`Projects`** \| **`Settings`** (hidden on `CraftingScreen`). Active-queue pill/FAB lives on `Catalog`; Registry Updates live inside `Settings`. |
| **Room Schema & Migrations** | Remove dead v1/v2 custom-item Room files in Phase 1 (eliminating startup DB open crashes). Re-introduce `AppDatabase` in Phase 3 at `version = 3` with `exportSchema = true` (`room.schemaLocation`) and explicit `Migration(1, 3)` and `Migration(2, 3)` dropping legacy tables (`items`, `recipes`, `categories`, `recipe_types`) and creating Saved Projects tables. |
| **Data Pipeline & OTA Updates** | Offline-first bundled JSON assets generated from Minecraft's `data/minecraft/recipe/` & `tags/item/` files, plus optional over-the-air JSON registry updates from a GitHub-hosted manifest (SHA-256 verified, Coil disk-cached remote icons for new MC items). |

---

## Phase Dependency Overview

```text
Phase 1: Core Engine & Cleanup
   ├── Pure demand-aggregated CraftingSolver + unit test suite
   ├── ViewModel StateFlow + per-ItemID recipe overrides + leftover bug fixes
   ├── Remove deprecated custom-item Room/route remnants & Gson
   └── Build alignment (compileSdk = 35, stable navigation-compose)
          │
          ▼
Phase 2: Crafting UX & QoL
   ├── Tabbed CraftingScreen (Materials | Steps | Tree) + top progress bar
   ├── Per-session "Have X" inventory & checklist + bottom-up workstation steps
   ├── Shulker/Double Chest stack formatting + quick-add chips + queue editing
   ├── Mini-JEI "Used In" reverse recipe lookup in ItemInfoDialog
   └── Pre-indexed Catalog search + single CrafterTheme + Minecraft palette/font
          │
          ▼
Phase 3: Persistence, Projects & Settings
   ├── Material 3 Bottom NavigationBar (Catalog | Projects | Settings)
   ├── Room DB v3 (Saved Projects + auto-saved active draft) & v1/v2 -> v3 migrations
   ├── Projects Screen + Text/Markdown export + Litematica (.txt/.csv) import
   └── Settings Screen (DataStore): fuel/wood/stone prefs, Stonecutter, theme, font, keep-screen-on
          │
          ▼
Phase 4: Data Pipeline & OTA Updates
   ├── Offline Minecraft data generator (tools/mc-data-generator/) -> bundled JSON assets
   ├── Tag-based ingredients (#planks, #logs, #wool, #stone) wired to Settings prefs
   ├── Systematic Tipped Arrows + Visual 3x3 Crafting Grid & station previews
   └── GitHub-hosted OTA manifest updater (SHA-256 atomic swap + Coil remote icons)
```

---

## Phase Documents

1. **[Phase 1 — Core Engine, Cleanup & Safety Fixes](phase-1-core-engine.md)**
2. **[Phase 2 — Crafting UX, Inventory Checklist & QoL](phase-2-crafting-ux-and-qol.md)**
3. **[Phase 3 — Saved Projects, Litematica Import, Navigation & Settings](phase-3-projects-settings-and-navigation.md)**
4. **[Phase 4 — Data-Driven JSON Pipeline, 3×3 Grids & OTA Updates](phase-4-data-pipeline-and-ota-updates.md)**

---

## Shared Verification Baseline

Run after every phase (and at the end of each self-contained milestone within a phase):

```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

When Room migrations or UI tests are added (Phase 3+):

```bash
./gradlew connectedDebugAndroidTest
```
