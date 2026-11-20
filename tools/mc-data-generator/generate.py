#!/usr/bin/env python3
"""
Offline Minecraft Registry Generator for Crafter.

Reads official Minecraft data JSON (from a cached/downloaded misode/mcmeta tarball,
extracted data directory, or local Minecraft client/server jar) plus Crafter's curated
overlays (item_metadata.json & special_recipes.json), and emits validated JSON assets
into app/src/main/assets/registry/ along with tinted Tipped Arrow PNG icons.
"""

from __future__ import annotations

import argparse
from datetime import datetime, timezone
import hashlib
import io
import json
from pathlib import Path
import tarfile
import urllib.request
import zipfile

try:
    from PIL import Image
except ImportError:
    Image = None

DEFAULT_MC_VERSION = "26.3"
SUPPORTED_SCHEMA_VERSION = 1
DEFAULT_ICON_BASE_URL = (
    "https://raw.githubusercontent.com/fracta7/crafter/master/app/src/main/assets/items/"
)

# Mojang renamed `chain` -> `iron_chain` in 1.21.9+. Map cleanly to Crafter's catalog ID.
ITEM_ID_ALIASES = {
    "iron_chain": "chain",
}

# Canonical RGB liquid colors for lingering potion effects (Tipped Arrow icon tinting).
POTION_EFFECT_COLORS = {
    "swiftness": (51, 235, 255),
    "slowness": (139, 175, 224),
    "leaping": (253, 255, 132),
    "strength": (255, 199, 0),
    "healing": (248, 36, 35),
    "harming": (169, 101, 106),
    "poison": (135, 163, 99),
    "regeneration": (205, 92, 171),
    "fire_resistance": (255, 153, 0),
    "water_breathing": (152, 218, 192),
    "night_vision": (194, 255, 102),
    "invisibility": (246, 246, 246),
    "turtle_master": (102, 74, 86),
    "slow_falling": (243, 207, 185),
    "weakness": (72, 77, 72),
    "wind_charging": (189, 201, 255),
    "weaving": (120, 105, 90),
    "oozing": (153, 255, 163),
    "infestation": (140, 155, 140),
}

# Preferred single base items when vanilla inline lists enumerate dyeable/variant blocks.
INLINE_LIST_CANONICAL_OVERRIDES = {
    ("minecraft:coal", "minecraft:charcoal"): ("tag", "minecraft:coals"),
    ("minecraft:purpur_block", "minecraft:purpur_pillar"): ("item", "purpur_block"),
    ("minecraft:chiseled_quartz_block", "minecraft:quartz_block", "minecraft:quartz_pillar"): (
        "item",
        "quartz_block",
    ),
    ("minecraft:red_sandstone", "minecraft:chiseled_red_sandstone"): ("item", "red_sandstone"),
    (
        "minecraft:red_sandstone",
        "minecraft:chiseled_red_sandstone",
        "minecraft:cut_red_sandstone",
    ): ("item", "red_sandstone"),
    ("minecraft:sandstone", "minecraft:chiseled_sandstone"): ("item", "sandstone"),
    ("minecraft:sandstone", "minecraft:chiseled_sandstone", "minecraft:cut_sandstone"): (
        "item",
        "sandstone",
    ),
    ("minecraft:copper_block", "minecraft:waxed_copper_block"): ("item", "copper_block"),
    ("minecraft:sand", "minecraft:red_sand"): ("tag", "minecraft:sand"),
}


def strip_ns(raw: str) -> str:
    return raw.removeprefix("minecraft:")


def normalize_item_id(raw: str) -> str:
    clean = strip_ns(raw.strip())
    return ITEM_ID_ALIASES.get(clean, clean)


def normalize_tag_id(raw: str) -> str:
    clean = raw.strip().lstrip("#")
    if ":" not in clean:
        clean = f"minecraft:{clean}"
    return clean


def load_mc_files_from_tarball(tar_path: Path) -> tuple[dict[str, dict], dict[str, dict]]:
    recipes_raw: dict[str, dict] = {}
    tags_raw: dict[str, dict] = {}
    with tarfile.open(tar_path, "r:gz") as tf:
        for member in tf.getmembers():
            if not member.isfile() or not member.name.endswith(".json"):
                continue
            if "/data/minecraft/recipe/" in member.name:
                key = member.name.split("/data/minecraft/recipe/", 1)[1][:-5]
                recipes_raw[key] = json.load(tf.extractfile(member))
            elif "/data/minecraft/tags/item/" in member.name:
                key = member.name.split("/data/minecraft/tags/item/", 1)[1][:-5]
                tags_raw[f"minecraft:{key}"] = json.load(tf.extractfile(member))
    return recipes_raw, tags_raw


def load_mc_files_from_dir(data_dir: Path) -> tuple[dict[str, dict], dict[str, dict]]:
    recipes_raw: dict[str, dict] = {}
    tags_raw: dict[str, dict] = {}
    mc_root = data_dir / "data" / "minecraft" if (data_dir / "data" / "minecraft").is_dir() else data_dir
    recipe_root = mc_root / "recipe"
    tag_root = mc_root / "tags" / "item"
    if recipe_root.is_dir():
        for path in sorted(recipe_root.rglob("*.json")):
            rel = path.relative_to(recipe_root).as_posix()[:-5]
            recipes_raw[rel] = json.loads(path.read_text(encoding="utf-8"))
    if tag_root.is_dir():
        for path in sorted(tag_root.rglob("*.json")):
            rel = path.relative_to(tag_root).as_posix()[:-5]
            tags_raw[f"minecraft:{rel}"] = json.loads(path.read_text(encoding="utf-8"))
    return recipes_raw, tags_raw


def load_mc_files_from_jar(jar_path: Path) -> tuple[dict[str, dict], dict[str, dict]]:
    recipes_raw: dict[str, dict] = {}
    tags_raw: dict[str, dict] = {}
    with zipfile.ZipFile(jar_path, "r") as zf:
        for name in sorted(zf.namelist()):
            if not name.endswith(".json"):
                continue
            if name.startswith("data/minecraft/recipe/"):
                key = name.removeprefix("data/minecraft/recipe/")[:-5]
                recipes_raw[key] = json.loads(zf.read(name).decode("utf-8"))
            elif name.startswith("data/minecraft/tags/item/"):
                key = name.removeprefix("data/minecraft/tags/item/")[:-5]
                tags_raw[f"minecraft:{key}"] = json.loads(zf.read(name).decode("utf-8"))
    return recipes_raw, tags_raw


def download_mcmeta_tarball(mc_version: str, cache_dir: Path) -> Path:
    cache_dir.mkdir(parents=True, exist_ok=True)
    target = cache_dir / f"mcmeta-{mc_version}.tar.gz"
    if target.exists() and target.stat().st_size > 10_000:
        return target
    url = f"https://github.com/misode/mcmeta/archive/refs/tags/{mc_version}-data-json.tar.gz"
    print(f"Downloading {url} -> {target} ...")
    req = urllib.request.Request(url, headers={"User-Agent": "CrafterDataGenerator/1.0"})
    with urllib.request.urlopen(req, timeout=30) as resp:
        data = resp.read()
    target.write_bytes(data)
    return target


def resolve_all_tags(tags_raw: dict[str, dict], valid_item_ids: set[str]) -> dict[str, list[str]]:
    resolved: dict[str, list[str]] = {}

    def resolve_one(tag_id: str, visiting: set[str]) -> list[str]:
        if tag_id in resolved:
            return resolved[tag_id]
        if tag_id in visiting:
            return []
        raw = tags_raw.get(tag_id)
        if not raw:
            return []
        visiting.add(tag_id)
        members: list[str] = []
        seen_members: set[str] = set()
        for entry in raw.get("values", []):
            val = entry.get("id") if isinstance(entry, dict) else entry
            if not isinstance(val, str):
                continue
            if val.startswith("#"):
                sub_items = resolve_one(normalize_tag_id(val), visiting)
                for item_id in sub_items:
                    if item_id not in seen_members:
                        seen_members.add(item_id)
                        members.append(item_id)
            else:
                item_id = normalize_item_id(val)
                if item_id in valid_item_ids and item_id not in seen_members:
                    seen_members.add(item_id)
                    members.append(item_id)
        visiting.remove(tag_id)
        resolved[tag_id] = members
        return members

    for tid in sorted(tags_raw.keys()):
        resolve_one(tid, set())

    # Ensure useful virtual tags always exist in item_tags.json
    if "minecraft:coals" not in resolved or not resolved["minecraft:coals"]:
        resolved["minecraft:coals"] = [i for i in ("coal", "charcoal") if i in valid_item_ids]
    if "minecraft:soul_fire_base_blocks" not in resolved or not resolved["minecraft:soul_fire_base_blocks"]:
        resolved["minecraft:soul_fire_base_blocks"] = [
            i for i in ("soul_sand", "soul_soil") if i in valid_item_ids
        ]

    return {k: v for k, v in sorted(resolved.items()) if v}


def parse_ingredient(
    raw_ing,
    valid_item_ids: set[str],
    resolved_tags: dict[str, list[str]],
) -> dict | None:
    """Normalizes MC 1.21.2+ compact or legacy object ingredients into IngredientRef JSON."""
    if raw_ing is None:
        return None
    if isinstance(raw_ing, dict):
        if "tag" in raw_ing:
            raw_ing = "#" + raw_ing["tag"]
        elif "item" in raw_ing:
            raw_ing = raw_ing["item"]
        else:
            return None

    if isinstance(raw_ing, str):
        if raw_ing.startswith("#"):
            tid = normalize_tag_id(raw_ing)
            if tid in resolved_tags and resolved_tags[tid]:
                return {"type": "tag", "id": tid}
            return None
        iid = normalize_item_id(raw_ing)
        if iid in valid_item_ids:
            return {"type": "item", "id": iid}
        return None

    if isinstance(raw_ing, list):
        if not raw_ing:
            return None
        # Recursively handle list of strings or list of dicts
        norm_strs = []
        for elem in raw_ing:
            if isinstance(elem, dict):
                if "item" in elem:
                    norm_strs.append(elem["item"])
                elif "tag" in elem:
                    norm_strs.append("#" + elem["tag"])
            elif isinstance(elem, str):
                norm_strs.append(elem)

        key_tuple = tuple(norm_strs)
        if key_tuple in INLINE_LIST_CANONICAL_OVERRIDES:
            kind, target = INLINE_LIST_CANONICAL_OVERRIDES[key_tuple]
            if kind == "tag" and target in resolved_tags and resolved_tags[target]:
                return {"type": "tag", "id": target}
            if kind == "item":
                iid = normalize_item_id(target)
                if iid in valid_item_ids:
                    return {"type": "item", "id": iid}

        # Pick first concrete valid item as deterministic fallback
        for s in norm_strs:
            ref = parse_ingredient(s, valid_item_ids, resolved_tags)
            if ref is not None:
                return ref
        return None

    return None


def ref_key(ref: dict) -> tuple[str, str]:
    return (ref["type"], ref["id"])


def add_ref_count(reqs: dict[str, int], tag_reqs: dict[str, int], ref: dict, count: int = 1) -> None:
    if ref["type"] == "item":
        reqs[ref["id"]] = reqs.get(ref["id"], 0) + count
    else:
        tag_reqs[ref["id"]] = tag_reqs.get(ref["id"], 0) + count


def should_skip_vanilla_recipe(recipe_key: str, data: dict) -> str | None:
    """
    Returns None to include, or handles special dye/transmute/smelt-armor rules.
    """
    rtype = strip_ns(data.get("type", ""))
    if rtype in (
        "crafting_special_armordye",
        "crafting_special_bannerduplicate",
        "crafting_special_bookcloning",
        "crafting_special_firework_rocket",
        "crafting_special_firework_star",
        "crafting_special_firework_star_fade",
        "crafting_special_mapcloning",
        "crafting_special_mapextending",
        "crafting_special_repairitem",
        "crafting_special_shielddecoration",
        "crafting_special_tippedarrow",
        "crafting_decorated_pot",
        "smithing_trim",
    ):
        return "unsupported_dynamic_type"

    if rtype == "crafting_transmute" and not recipe_key.endswith("_bundle"):
        return "unsupported_transmute"

    # Skip re-dyeing back into white wool/carpet/bed/harness/cushion/wool_slab/wool_stairs to avoid dye-to-white cycles.
    if recipe_key in (
        "dye_white_wool",
        "dye_white_carpet",
        "dye_white_bed",
        "dye_white_harness",
        "dye_white_cushion",
        "dye_white_wool_slab",
        "dye_white_wool_stairs",
    ):
        return "white_redye_cycle"

    # Skip melting armor/tools back into 1 nugget (cycles back to ingots; ingot->9 nuggets is used instead).
    if recipe_key.startswith(("iron_nugget_from_", "gold_nugget_from_", "copper_nugget_from_")):
        return "tool_melt_cycle"

    return None


def parse_vanilla_recipe(
    recipe_key: str,
    data: dict,
    valid_item_ids: set[str],
    resolved_tags: dict[str, list[str]],
) -> dict | None:
    if should_skip_vanilla_recipe(recipe_key, data) is not None:
        return None

    mc_type = strip_ns(data.get("type", ""))
    res_obj = data.get("result")
    if not isinstance(res_obj, dict) or "id" not in res_obj:
        return None
    result_id = normalize_item_id(res_obj["id"])
    if result_id not in valid_item_ids:
        return None
    amount = int(res_obj.get("count", 1))
    if amount <= 0:
        return None

    reqs: dict[str, int] = {}
    tag_reqs: dict[str, int] = {}

    if mc_type == "crafting_shaped":
        pattern = [row[:3] for row in data.get("pattern", [])[:3]]
        raw_key = data.get("key", {})
        if not pattern or not raw_key:
            return None
        used_symbols = {ch for row in pattern for ch in row if ch != " "}
        layout_key: dict[str, dict] = {}
        for sym in sorted(used_symbols):
            if sym not in raw_key:
                return None
            ref = parse_ingredient(raw_key[sym], valid_item_ids, resolved_tags)
            if ref is None:
                return None
            layout_key[sym] = ref

        for row in pattern:
            for ch in row:
                if ch != " ":
                    add_ref_count(reqs, tag_reqs, layout_key[ch], 1)

        return {
            "result": result_id,
            "amount": amount,
            "recipeType": "crafting_shaped",
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "shaped",
                "pattern": pattern,
                "key": layout_key,
            },
        }

    if mc_type == "crafting_shapeless":
        raw_ings = data.get("ingredients", [])
        if not raw_ings:
            return None
        layout_ings: list[dict] = []

        # For dye_<color>_<item> shapeless recipes, force the white base item + color dye
        if recipe_key.startswith("dye_"):
            for raw_ing in raw_ings:
                if isinstance(raw_ing, list) and len(raw_ing) == 15:
                    if recipe_key.endswith("_wool"):
                        ref = {"type": "item", "id": "white_wool"}
                    elif recipe_key.endswith("_carpet"):
                        ref = {"type": "item", "id": "white_carpet"}
                    elif recipe_key.endswith("_bed"):
                        ref = {"type": "item", "id": "white_bed"}
                    elif recipe_key.endswith("_harness"):
                        ref = {"type": "item", "id": "white_harness"}
                    elif recipe_key.endswith("_cushion"):
                        ref = {"type": "item", "id": "white_cushion"}
                    elif recipe_key.endswith("_wool_slab"):
                        ref = {"type": "item", "id": "white_wool_slab"}
                    elif recipe_key.endswith("_wool_stairs"):
                        ref = {"type": "item", "id": "white_wool_stairs"}
                    else:
                        ref = parse_ingredient(raw_ing, valid_item_ids, resolved_tags)
                else:
                    ref = parse_ingredient(raw_ing, valid_item_ids, resolved_tags)
                if ref is None:
                    return None
                layout_ings.append(ref)
                add_ref_count(reqs, tag_reqs, ref, 1)
        else:
            for raw_ing in raw_ings:
                ref = parse_ingredient(raw_ing, valid_item_ids, resolved_tags)
                if ref is None:
                    return None
                layout_ings.append(ref)
                add_ref_count(reqs, tag_reqs, ref, 1)

        return {
            "result": result_id,
            "amount": amount,
            "recipeType": "crafting_shapeless",
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "shapeless",
                "ingredients": layout_ings,
            },
        }

    if mc_type == "crafting_transmute" and recipe_key.endswith("_bundle"):
        mat_ref = parse_ingredient(data.get("material"), valid_item_ids, resolved_tags)
        if mat_ref is None or "bundle" not in valid_item_ids:
            return None
        base_ref = {"type": "item", "id": "bundle"}
        add_ref_count(reqs, tag_reqs, base_ref, 1)
        add_ref_count(reqs, tag_reqs, mat_ref, 1)
        return {
            "result": result_id,
            "amount": amount,
            "recipeType": "crafting_shapeless",
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "shapeless",
                "ingredients": [base_ref, mat_ref],
            },
        }

    if mc_type in ("smelting", "blasting", "smoking", "campfire_cooking"):
        ref = parse_ingredient(data.get("ingredient"), valid_item_ids, resolved_tags)
        if ref is None:
            return None
        add_ref_count(reqs, tag_reqs, ref, 1)
        requires_fuel = mc_type != "campfire_cooking"
        if requires_fuel:
            reqs["fuel"] = 1
        return {
            "result": result_id,
            "amount": amount,
            "recipeType": mc_type,
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "furnace",
                "input": ref,
                "requiresFuel": requires_fuel,
            },
        }

    if mc_type == "stonecutting":
        ref = parse_ingredient(data.get("ingredient"), valid_item_ids, resolved_tags)
        if ref is None:
            return None
        add_ref_count(reqs, tag_reqs, ref, 1)
        return {
            "result": result_id,
            "amount": amount,
            "recipeType": "stonecutting",
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "single",
                "input": ref,
            },
        }

    if mc_type == "smithing_transform":
        template = parse_ingredient(data.get("template"), valid_item_ids, resolved_tags)
        base = parse_ingredient(data.get("base"), valid_item_ids, resolved_tags)
        addition = parse_ingredient(data.get("addition"), valid_item_ids, resolved_tags)
        if template is None or base is None or addition is None:
            return None
        add_ref_count(reqs, tag_reqs, template, 1)
        add_ref_count(reqs, tag_reqs, base, 1)
        add_ref_count(reqs, tag_reqs, addition, 1)
        return {
            "result": result_id,
            "amount": amount,
            "recipeType": "smithing_transform",
            "requirements": reqs,
            "tagRequirements": tag_reqs,
            "layout": {
                "type": "smithing",
                "template": template,
                "base": base,
                "addition": addition,
            },
        }

    return None


def tipped_arrow_meta(potion_id: str, potion_name: str) -> tuple[str, str, tuple[int, int, int]]:
    """
    Converts e.g. `swiftness_lingering_potion_2` -> (`swiftness_tipped_arrow_2`,
    `Arrow of Swiftness II`, RGB tint).
    """
    base_name = potion_name.replace("Lingering Potion of ", "Arrow of ")
    # Strip duration suffix cleanly while keeping level II / IV indicators
    parts = base_name.split(" ")
    if parts and ":" in parts[-1]:
        clean_name = " ".join(parts[:-1])
    else:
        clean_name = base_name

    if potion_id.endswith("_2"):
        arrow_id = potion_id.replace("_lingering_potion_2", "_tipped_arrow_2")
        effect = potion_id.removesuffix("_lingering_potion_2")
    elif potion_id.endswith("_e"):
        arrow_id = potion_id.replace("_lingering_potion_e", "_tipped_arrow_e")
        clean_name = f"{clean_name} (Extended)"
        effect = potion_id.removesuffix("_lingering_potion_e")
    else:
        arrow_id = potion_id.replace("_lingering_potion", "_tipped_arrow")
        effect = potion_id.removesuffix("_lingering_potion")

    rgb = POTION_EFFECT_COLORS.get(effect, (160, 120, 220))
    return arrow_id, clean_name, rgb


def generate_tipped_arrow_icon(
    icons_dir: Path,
    base_tipped_img,
    arrow_img,
    rgb: tuple[int, int, int],
    out_arrow_id: str,
) -> None:
    if Image is None:
        return
    out_path = icons_dir / f"{out_arrow_id}.png"
    tinted = base_tipped_img.copy()
    tr, tg, tb = rgb
    for y in range(tinted.height):
        for x in range(tinted.width):
            p_tip = base_tipped_img.getpixel((x, y))
            p_arr = arrow_img.getpixel((x, y))
            if p_tip != p_arr and p_tip[3] > 0:
                lum = p_tip[0] / 247.0
                r = max(0, min(255, int(round(tr * lum))))
                g = max(0, min(255, int(round(tg * lum))))
                b = max(0, min(255, int(round(tb * lum))))
                tinted.putpixel((x, y), (r, g, b, p_tip[3]))
    tinted.save(out_path, format="PNG")


def recipe_dedupe_key(r: dict) -> tuple:
    return (
        r["result"],
        r["amount"],
        r["recipeType"],
        tuple(sorted(r.get("requirements", {}).items())),
        tuple(sorted(r.get("tagRequirements", {}).items())),
    )


def write_deterministic_json(path: Path, payload: object) -> tuple[str, int]:
    raw = (json.dumps(payload, indent=2, ensure_ascii=False) + "\n").encode("utf-8")
    path.write_bytes(raw)
    digest = hashlib.sha256(raw).hexdigest()
    return digest, len(raw)


def main() -> None:
    parser = argparse.ArgumentParser(description="Crafter Minecraft Data JSON Generator")
    parser.add_argument("--mc-version", default=DEFAULT_MC_VERSION)
    parser.add_argument("--mc-data-dir", type=Path, default=None)
    parser.add_argument("--mc-jar", type=Path, default=None)
    parser.add_argument("--registry-version", type=int, default=1)
    parser.add_argument("--output-dir", type=Path, default=Path("app/src/main/assets/registry"))
    parser.add_argument("--icons-dir", type=Path, default=Path("app/src/main/assets/items"))
    parser.add_argument("--icon-base-url", default=DEFAULT_ICON_BASE_URL)
    args = parser.parse_args()

    script_dir = Path(__file__).resolve().parent
    data_overlay_dir = script_dir / "data"
    item_meta = json.loads((data_overlay_dir / "item_metadata.json").read_text(encoding="utf-8"))
    special_data = json.loads(
        (data_overlay_dir / "special_recipes.json").read_text(encoding="utf-8")
    )

    if args.mc_data_dir is not None:
        recipes_raw, tags_raw = load_mc_files_from_dir(args.mc_data_dir)
    elif args.mc_jar is not None:
        recipes_raw, tags_raw = load_mc_files_from_jar(args.mc_jar)
    else:
        tar_path = download_mcmeta_tarball(args.mc_version, script_dir / ".cache")
        recipes_raw, tags_raw = load_mc_files_from_tarball(tar_path)

    items: list[dict] = list(item_meta["items"])
    valid_item_ids: set[str] = {i["id"] for i in items}

    # Expand Tipped Arrows before tag resolution so every arrow exists cleanly
    base_tipped_img = None
    arrow_img = None
    if Image is not None and (args.icons_dir / "tipped_arrow.png").exists():
        base_tipped_img = Image.open(args.icons_dir / "tipped_arrow.png").convert("RGBA")
        arrow_img = Image.open(args.icons_dir / "arrow.png").convert("RGBA")

    tipped_arrow_recipes: list[dict] = []
    tipped_arrow_count = 0
    for item in list(items):
        iid = item["id"]
        if "lingering_potion" not in iid or iid == "lingering_potion":
            continue
        arrow_id, arrow_name, rgb = tipped_arrow_meta(iid, item["name"])
        if arrow_id not in valid_item_ids:
            items.append(
                {
                    "id": arrow_id,
                    "name": arrow_name,
                    "stackSize": 64,
                    "decomposable": True,
                    "tags": ["combat", "potions"],
                }
            )
            valid_item_ids.add(arrow_id)
            tipped_arrow_count += 1
        if base_tipped_img is not None and arrow_img is not None:
            generate_tipped_arrow_icon(args.icons_dir, base_tipped_img, arrow_img, rgb, arrow_id)
        tipped_arrow_recipes.append(
            {
                "result": arrow_id,
                "amount": 8,
                "recipeType": "crafting_shaped",
                "requirements": {"arrow": 8, iid: 1},
                "tagRequirements": {},
                "layout": {
                    "type": "shaped",
                    "pattern": ["AAA", "APA", "AAA"],
                    "key": {
                        "A": {"type": "item", "id": "arrow"},
                        "P": {"type": "item", "id": iid},
                    },
                },
            }
        )

    resolved_tags = resolve_all_tags(tags_raw, valid_item_ids)

    all_recipes: list[dict] = []
    seen_recipes: set[tuple] = set()

    def register_recipe(rec: dict) -> None:
        k = recipe_dedupe_key(rec)
        if k not in seen_recipes:
            seen_recipes.add(k)
            all_recipes.append(rec)

    for key in sorted(recipes_raw.keys()):
        parsed = parse_vanilla_recipe(key, recipes_raw[key], valid_item_ids, resolved_tags)
        if parsed is not None:
            register_recipe(parsed)

    for sr in special_data["special_recipes"]:
        rec = {
            "result": sr["result"],
            "amount": int(sr["amount"]),
            "recipeType": sr["recipeType"],
            "requirements": sr.get("requirements", {}),
            "tagRequirements": sr.get("tagRequirements", {}),
            "layout": sr.get("layout"),
        }
        if rec["result"] in valid_item_ids and all(
            req in valid_item_ids for req in rec["requirements"]
        ):
            register_recipe(rec)

    for tr in tipped_arrow_recipes:
        register_recipe(tr)

    # Verify every decomposable item has >= 1 recipe
    results_with_recipes = {r["result"] for r in all_recipes}
    missing_decomposable = [
        i["id"] for i in items if i["decomposable"] and i["id"] not in results_with_recipes
    ]
    if missing_decomposable:
        raise RuntimeError(
            f"Decomposable items missing recipes: {missing_decomposable}"
        )

    args.output_dir.mkdir(parents=True, exist_ok=True)
    file_checksums: dict[str, dict] = {}
    outputs = {
        "items.json": items,
        "recipes.json": all_recipes,
        "item_tags.json": resolved_tags,
        "categories.json": special_data["categories"],
        "recipe_types.json": special_data["recipe_types"],
    }
    for fname, data in outputs.items():
        digest, size = write_deterministic_json(args.output_dir / fname, data)
        file_checksums[fname] = {"sha256": digest, "bytes": size}

    manifest = {
        "schemaVersion": SUPPORTED_SCHEMA_VERSION,
        "mcVersion": args.mc_version,
        "registryVersion": args.registry_version,
        "generatedAt": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "iconBaseUrl": args.icon_base_url,
        "files": file_checksums,
    }
    (args.output_dir / "manifest.json").write_text(
        json.dumps(manifest, indent=2, ensure_ascii=False) + "\n",
        encoding="utf-8",
    )

    print(
        f"Generated registry v{args.registry_version} for MC {args.mc_version}: "
        f"{len(items)} items (+{tipped_arrow_count} tipped arrows), "
        f"{len(all_recipes)} recipes, {len(resolved_tags)} tags -> {args.output_dir}"
    )


if __name__ == "__main__":
    main()
