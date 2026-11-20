# Crafter Minecraft Data Generator

Generates the bundled offline-first JSON registry under `app/src/main/assets/registry/` and tinted Tipped Arrow item sprites under `app/src/main/assets/items/` from official Minecraft data JSON + Crafter's curated overlays.

## Inputs
- Official Minecraft `data/minecraft/recipe/*.json` & `data/minecraft/tags/item/*.json` (automatically fetched from `misode/mcmeta` tags or supplied locally via `--mc-data-dir` / `--mc-jar`).
- `data/item_metadata.json`: Display names, UI categories, stack sizes, and decomposable flags.
- `data/special_recipes.json`: Non-JSON mechanics (`oxidation`, `stripping`, `watering`, `waxing`, `fuel`, `brewing`, `shulker_box` dyeing, `decorated_pot`, `categories`, and `recipe_types`).

## Usage

```bash
# Regenerate MC 26.3 bundled registry (downloads & caches misode/mcmeta tarball)
python3 tools/mc-data-generator/generate.py \
  --mc-version 26.3 \
  --registry-version 1

# Publish an OTA update bump from a local Minecraft jar or extracted data directory
python3 tools/mc-data-generator/generate.py \
  --mc-jar ~/path/to/client.jar \
  --mc-version 26.4 \
  --registry-version 2
```

## Outputs (`app/src/main/assets/registry/`)
- `manifest.json` — schema version, MC version, monotonic registry version, icon CDN base URL, and SHA-256 checksums + byte sizes.
- `items.json` — all items including generated Tipped Arrows.
- `recipes.json` — concrete & `#minecraft:*` tag recipes with structured `CraftingShaped` (3×3), `CraftingShapeless`, `Furnace`, `Brewing`, `Smithing`, and `SingleInput` slot layouts.
- `item_tags.json` — flattened Minecraft item tags (`minecraft:planks`, `minecraft:logs_that_burn`, `minecraft:stone_crafting_materials`, etc.).
- `categories.json` & `recipe_types.json` — UI categories and workstation definitions.
