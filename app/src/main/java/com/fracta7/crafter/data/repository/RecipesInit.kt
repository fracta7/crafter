package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.Recipe

/**
 * Initiates all recipes
 * @return List of Recipes.
 */
fun recipesInit(): List<Recipe> {
    return listOf(
        Recipe(
            result = "polished_granite",
            resultQuantity = 1,
            requirements = mapOf("granite" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_diorite",
            resultQuantity = 1,
            requirements = mapOf("diorite" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_andesite",
            resultQuantity = 1,
            requirements = mapOf("andesite" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_deepslate",
            resultQuantity = 1,
            requirements = mapOf("cobbled_deepslate" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "coarse_dirt",
            resultQuantity = 4,
            requirements = mapOf(
                "gravel" to 2,
                "sand" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_planks",
            resultQuantity = 4,
            requirements = mapOf("oak_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_planks",
            resultQuantity = 4,
            requirements = mapOf("spruce_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_planks",
            resultQuantity = 4,
            requirements = mapOf("birch_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_planks",
            resultQuantity = 4,
            requirements = mapOf("jungle_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_planks",
            resultQuantity = 4,
            requirements = mapOf("acacia_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_planks",
            resultQuantity = 4,
            requirements = mapOf("dark_oak_log" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_planks",
            resultQuantity = 4,
            requirements = mapOf("crimson_stem" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_planks",
            resultQuantity = 4,
            requirements = mapOf("warped_stem" to 1),
            recipeType = "crafting"
        ),
        Recipe(
            result = "coal_block",
            resultQuantity = 1,
            requirements = mapOf("coal" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "raw_iron_block",
            resultQuantity = 1,
            requirements = mapOf("raw_iron" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "raw_copper_block",
            resultQuantity = 1,
            requirements = mapOf("raw_copper" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "raw_gold_block",
            resultQuantity = 1,
            requirements = mapOf("raw_gold" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "amethyst_block",
            resultQuantity = 1,
            requirements = mapOf("amethyst_shard" to 4),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_block",
            resultQuantity = 1,
            requirements = mapOf("iron_ingot" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "copper_block",
            resultQuantity = 1,
            requirements = mapOf("copper_ingot" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gold_block",
            resultQuantity = 1,
            requirements = mapOf("gold_ingot" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_block",
            resultQuantity = 1,
            requirements = mapOf("diamond" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "netherite_block",
            resultQuantity = 1,
            requirements = mapOf("netherite_ingot" to 9),
            recipeType = "crafting"
        ),
        Recipe(
            result = "exposed_copper",
            resultQuantity = 1,
            requirements = mapOf("copper_block" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_copper",
            resultQuantity = 1,
            requirements = mapOf("copper_block" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_copper",
            resultQuantity = 1,
            requirements = mapOf("copper_block" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "cut_copper",
            resultQuantity = 1,
            requirements = mapOf("copper_block" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "exposed_cut_copper",
            resultQuantity = 1,
            requirements = mapOf("cut_copper" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_cut_copper",
            resultQuantity = 1,
            requirements = mapOf("cut_copper" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_cut_copper",
            resultQuantity = 1,
            requirements = mapOf("cut_copper" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf("copper_block" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "exposed_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_stairs" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_stairs" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_stairs" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "cut_copper_slab",
            resultQuantity = 2,
            requirements = mapOf("copper_block" to 1),
            recipeType = "cutting"
        ),
        Recipe(
            result = "exposed_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_slab" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_slab" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf("cut_copper_slab" to 1),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "waxed_copper_block",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_block" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_cut_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "cut_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_cut_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_cut_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_cut_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_cut_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_cut_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cut_copper_stairs" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_cut_copper_stairs" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_cut_copper_stairs" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_cut_copper_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper_stairs" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "stripped_oak_log",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_spruce_log",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_birch_log",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_jungle_log",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_acacia_log",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_dark_oak_log",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_crimson_stem",
            resultQuantity = 1,
            requirements = mapOf(
                "crimson_stem" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_warped_stem",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_stem" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_oak_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_spruce_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_birch_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_jungle_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_acacia_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_dark_oak_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_crimson_hyphae",
            resultQuantity = 1,
            requirements = mapOf(
                "crimson_hyphae" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_warped_hyphae",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_hyphae" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "oak_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "oak_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "spruce_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "birch_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "jungle_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "acacia_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "dark_oak_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_hyphae",
            resultQuantity = 3,
            requirements = mapOf(
                "crimson_stem" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_hyphae",
            resultQuantity = 3,
            requirements = mapOf(
                "warped_stem" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "glass",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "tinted_glass",
            resultQuantity = 2,
            requirements = mapOf(
                "glass" to 1,
                "amethyst_shard" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lapis_block",
            resultQuantity = 1,
            requirements = mapOf(
                "lapis_lazuli" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cut_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "orange_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "light_blue_wool" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "light_gray_wool" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_dye" to 1,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_wool",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "moss_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "moss_block" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "oak_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "spruce_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "birch_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "jungle_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "acacia_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "dark_oak_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "crimson_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "warped_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_stone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "smooth_stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cut_sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cobblestone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "stone_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "nether_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "quartz_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cut_red_sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "purpur_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "purpur_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "prismarine_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "prismarine" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "prismarine_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "prismarine_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "dark_prismarine_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "dark_prismarine" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_quartz",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "smooth_red_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "smooth_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "smooth_stone",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "brick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bookshelf",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 6,
                "book" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mossy_cobblestone",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 1,
                "vine" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "torch",
            resultQuantity = 4,
            requirements = mapOf(
                "stick" to 1,
                "coal" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "end_rod",
            resultQuantity = 4,
            requirements = mapOf(
                "blaze_rod" to 1,
                "popped_chorus_fruit" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purpur_block",
            resultQuantity = 4,
            requirements = mapOf(
                "popped_chorus_fruit" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purpur_pillar",
            resultQuantity = 1,
            requirements = mapOf(
                "purpur_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "purpur_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "purpur_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "oak_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chest",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crafting_table",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "furnace",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "ladder",
            resultQuantity = 3,
            requirements = mapOf(
                "stick" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cobblestone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "snow_block",
            resultQuantity = 1,
            requirements = mapOf(
                "snowball" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jukebox",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 8,
                "diamond" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "oak_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "spruce_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "birch_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "jungle_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "acacia_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "dark_oak_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "crimson_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "warped_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "carved_pumpkin",
            resultQuantity = 1,
            requirements = mapOf(
                "pumpkin" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "jack_o_lantern",
            resultQuantity = 3,
            requirements = mapOf(
                "carved_pumpkin" to 1,
                "torch" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_basalt",
            resultQuantity = 1,
            requirements = mapOf(
                "basalt" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "soul_torch",
            resultQuantity = 4,
            requirements = mapOf(
                "coal" to 1,
                "stick" to 1,
                "soul_sand" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "glowstone",
            resultQuantity = 1,
            requirements = mapOf(
                "glowstone_dust" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_stone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "stone_bricks" to 1,
                "vine" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cracked_stone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "stone_bricks" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "chiseled_stone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "deepslate_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cracked_deepslate_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "deepslate_bricks" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "deepslate_tiles",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cracked_deepslate_tiles",
            resultQuantity = 1,
            requirements = mapOf(
                "deepslate_tiles" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "chiseled_deepslate",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "iron_bars",
            resultQuantity = 16,
            requirements = mapOf(
                "iron_ingot" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chain",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 1,
                "iron_nugget" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "melon",
            resultQuantity = 1,
            requirements = mapOf(
                "melon_slice" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "stone_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "stone_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "nether_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_brick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cracked_nether_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_bricks" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "chiseled_nether_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "nether_brick_fence",
            resultQuantity = 6,
            requirements = mapOf(
                "nether_bricks" to 4,
                "nether_brick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "nether_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "enchanting_table",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 2,
                "book" to 1,
                "obsidian" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "end_stone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "end_stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "sandstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "ender_chest",
            resultQuantity = 1,
            requirements = mapOf(
                "obsidian" to 8,
                "ender_eye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "emerald_block",
            resultQuantity = 1,
            requirements = mapOf(
                "emerald" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "spruce_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "birch_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "jungle_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "crimson_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "warped_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "beacon",
            resultQuantity = 1,
            requirements = mapOf(
                "glass" to 5,
                "obsidian" to 3,
                "nether_star" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cobblestone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_cobblestone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "mossy_cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "prismarine_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_sandstone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_stone_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "mossy_stone_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "granite_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "granite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "stone_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "nether_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "andesite_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "andesite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_nether_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "red_nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "sandstone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "end_stone_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "end_stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "diorite_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "diorite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "blackstone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cobbled_deepslate_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_deepslate_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_tile_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "anvil",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 4,
                "iron_block" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_quartz_block",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "quartz_block",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "quartz_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "quartz_pillar",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "quartz_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "quartz_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "white_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_terracotta",
            resultQuantity = 8,
            requirements = mapOf(
                "terracotta" to 8,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "hay_block",
            resultQuantity = 8,
            requirements = mapOf(
                "wheat" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "white_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "orange_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "magenta_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "light_blue_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "yellow_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "lime_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "pink_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "gray_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "light_gray_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "cyan_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "purple_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "blue_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "brown_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "green_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "red_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_carpet",
            resultQuantity = 3,
            requirements = mapOf(
                "black_wool" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "packed_ice",
            resultQuantity = 1,
            requirements = mapOf(
                "ice" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "acacia_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "dark_oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_stained_glass",
            resultQuantity = 8,
            requirements = mapOf(
                "glass" to 8,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_stained_glass_pane",
            resultQuantity = 8,
            requirements = mapOf(
                "glass_pane" to 8,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "prismarine",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine_shard" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "prismarine_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine_shard" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_prismarine",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine_shard" to 8,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "prismarine_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "prismarine_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "dark_prismarine_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_prismarine" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "sea_lantern",
            resultQuantity = 1,
            requirements = mapOf(
                "prismarine_shard" to 4,
                "prismarine_crystals" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sand" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_red_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cut_red_sandstone",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_sandstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "magma_block",
            resultQuantity = 1,
            requirements = mapOf(
                "magma_cream" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "nether_wart_block",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_wart" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_nether_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "nether_brick" to 2,
                "nether_wart" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bone_block",
            resultQuantity = 1,
            requirements = mapOf(
                "bone_meal" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_shell" to 2,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "white_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "orange_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "orange_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "magenta_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "magenta_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "light_blue_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "light_blue_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "yellow_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "yellow_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "lime_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "lime_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "pink_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "pink_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "gray_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "gray_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "light_gray_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "light_gray_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "cyan_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "cyan_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "purple_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "purple_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "blue_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "blue_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "brown_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "brown_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "green_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "green_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "red_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "red_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "black_glazed_terracotta",
            resultQuantity = 1,
            requirements = mapOf(
                "black_terracotta" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "white_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "white_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "orange_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "orange_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "magenta_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "magenta_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "light_blue_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "light_blue_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "yellow_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "yellow_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "lime_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "lime_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "pink_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "pink_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "gray_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "gray_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "light_gray_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "light_gray_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "cyan_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "cyan_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "purple_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "purple_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "blue_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "blue_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "brown_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "brown_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "green_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "green_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "red_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "red_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "black_concrete",
            resultQuantity = 1,
            requirements = mapOf(
                "black_concrete_powder" to 1
            ),
            recipeType = "watering"
        ),
        Recipe(
            result = "white_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_concrete_powder",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gravel" to 4,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_ice",
            resultQuantity = 1,
            requirements = mapOf(
                "packed_ice" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "conduit",
            resultQuantity = 1,
            requirements = mapOf(
                "heart_of_the_sea" to 1,
                "nautilus_shell" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_granite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "granite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_red_sandstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "smooth_red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_stone_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "mossy_stone_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_diorite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "diorite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_cobblestone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "mossy_cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "end_stone_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "end_stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "stone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_sandstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "smooth_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_quartz_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "smooth_quartz" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "granite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "granite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "andesite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "andesite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_nether_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "red_nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_andesite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "andesite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "diorite_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "diorite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cobbled_deepslate_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_deepslate_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_tile_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_granite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "granite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_red_sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "smooth_red_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_stone_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "mossy_stone_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_diorite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "diorite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mossy_cobblestone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "mossy_cobblestone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "end_stone_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "end_stone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_sandstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "smooth_sandstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "smooth_quartz_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "smooth_quartz" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "granite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "granite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "andesite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "andesite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "red_nether_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "red_nether_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_andesite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "andesite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "diorite_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "diorite" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "cobbled_deepslate_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_deepslate_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "deepslate_tile_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "cobbled_deepslate" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "scaffolding",
            resultQuantity = 6,
            requirements = mapOf(
                "bamboo" to 6,
                "string" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "redstone_torch",
            resultQuantity = 1,
            requirements = mapOf(
                "redstone" to 1,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "redstone_block",
            resultQuantity = 1,
            requirements = mapOf(
                "redstone" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "repeater",
            resultQuantity = 1,
            requirements = mapOf(
                "redstone_torch" to 2,
                "redstone" to 1,
                "stone" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "comparator",
            resultQuantity = 1,
            requirements = mapOf(
                "redstone_torch" to 3,
                "quartz" to 1,
                "stone" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "piston",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 3,
                "iron_ingot" to 1,
                "cobblestone" to 4,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "sticky_piston",
            resultQuantity = 1,
            requirements = mapOf(
                "piston" to 1,
                "slime_ball" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "slime_block",
            resultQuantity = 1,
            requirements = mapOf(
                "slime_ball" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "honey_block",
            resultQuantity = 1,
            requirements = mapOf(
                "honey_bottle" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "observer",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 6,
                "redstone" to 2,
                "quartz" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "hopper",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 5,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dispenser",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 7,
                "redstone" to 1,
                "bow" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dropper",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 7,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lectern",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_slab" to 4,
                "bookshelf" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "target",
            resultQuantity = 1,
            requirements = mapOf(
                "redstone" to 4,
                "hay_block" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lever",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 1,
                "cobblestone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lightning_rod",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_ingot" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "daylight_detector",
            resultQuantity = 1,
            requirements = mapOf(
                "glass" to 3,
                "quartz" to 3,
                "oak_slab" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tripwire_hook",
            resultQuantity = 2,
            requirements = mapOf(
                "iron_ingot" to 1,
                "stick" to 1,
                "oak_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "trapped_chest",
            resultQuantity = 1,
            requirements = mapOf(
                "chest" to 1,
                "tripwire_hook" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tnt",
            resultQuantity = 1,
            requirements = mapOf(
                "sand" to 4,
                "gunpowder" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "redstone_lamp",
            resultQuantity = 1,
            requirements = mapOf(
                "glowstone" to 1,
                "redstone" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "note_block",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 8,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_button",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_blackstone_button",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_blackstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_button",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_button",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_button",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_button",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_button",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_button",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_button",
            resultQuantity = 1,
            requirements = mapOf(
                "crimson_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_button",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_blackstone_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_blackstone" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_weighted_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "heavy_weighted_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "crimson_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_door",
            resultQuantity = 3,
            requirements = mapOf(
                "iron_ingot" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_door",
            resultQuantity = 3,
            requirements = mapOf(
                "oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_door",
            resultQuantity = 3,
            requirements = mapOf(
                "spruce_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_door",
            resultQuantity = 3,
            requirements = mapOf(
                "birch_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_door",
            resultQuantity = 3,
            requirements = mapOf(
                "jungle_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_door",
            resultQuantity = 3,
            requirements = mapOf(
                "acacia_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_door",
            resultQuantity = 3,
            requirements = mapOf(
                "dark_oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_door",
            resultQuantity = 3,
            requirements = mapOf(
                "crimson_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_door",
            resultQuantity = 3,
            requirements = mapOf(
                "warped_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "spruce_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "birch_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "jungle_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "acacia_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "dark_oak_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "crimson_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "warped_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "crimson_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "powered_rail",
            resultQuantity = 6,
            requirements = mapOf(
                "gold_ingot" to 6,
                "redstone" to 1,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "detector_rail",
            resultQuantity = 6,
            requirements = mapOf(
                "iron_ingot" to 6,
                "redstone" to 1,
                "stone_pressure_plate" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "rail",
            resultQuantity = 16,
            requirements = mapOf(
                "iron_ingot" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "activator_rail",
            resultQuantity = 6,
            requirements = mapOf(
                "iron_ingot" to 6,
                "stick" to 2,
                "redstone_torch" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "minecart",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chest_minecart",
            resultQuantity = 1,
            requirements = mapOf(
                "minecart" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "furnace_minecart",
            resultQuantity = 1,
            requirements = mapOf(
                "minecart" to 1,
                "furnace" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tnt_minecart",
            resultQuantity = 1,
            requirements = mapOf(
                "minecart" to 1,
                "tnt" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "hopper_minecart",
            resultQuantity = 1,
            requirements = mapOf(
                "minecart" to 1,
                "hopper" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "carrot_on_a_stick",
            resultQuantity = 1,
            requirements = mapOf(
                "carrot" to 1,
                "fishing_rod" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_fungus_on_a_stick",
            resultQuantity = 1,
            requirements = mapOf(
                "warped_fungus" to 1,
                "fishing_rod" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "turtle_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "scute" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "flint_and_steel",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 1,
                "flint" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bow",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 3,
                "string" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "arrow",
            resultQuantity = 4,
            requirements = mapOf(
                "stick" to 1,
                "flint" to 1,
                "feather" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "charcoal",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_log" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "netherite_ingot",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 4,
                "netherite_scrap" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "netherite_scrap",
            resultQuantity = 1,
            requirements = mapOf(
                "ancient_debris" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "wooden_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 2,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "wooden_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 1,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "wooden_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "wooden_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "wooden_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 2,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 2,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 1,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stone_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 2,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 2,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 1,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 2,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 2,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 1,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 2,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 2,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 1,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 3,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 2,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "netherite_sword",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_sword" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_shovel",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_shovel" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_pickaxe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_pickaxe" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_axe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_axe" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_hoe",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_hoe" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "stick",
            resultQuantity = 4,
            requirements = mapOf(
                "oak_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bowl",
            resultQuantity = 4,
            requirements = mapOf(
                "oak_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mushroom_stew",
            resultQuantity = 1,
            requirements = mapOf(
                "bowl" to 1,
                "brown_mushroom" to 1,
                "red_mushroom" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bread",
            resultQuantity = 1,
            requirements = mapOf(
                "wheat" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "leather_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "leather" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "leather_chestplate",
            resultQuantity = 1,
            requirements = mapOf(
                "leather" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "leather_leggings",
            resultQuantity = 1,
            requirements = mapOf(
                "leather" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "leather_boots",
            resultQuantity = 1,
            requirements = mapOf(
                "leather" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_chestplate",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_leggings",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_boots",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_chestplate",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_leggings",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_boots",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_chestplate",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_leggings",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "diamond_boots",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "netherite_helmet",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_helmet" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_chestplate",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_chestplate" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_leggings",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_leggings" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "netherite_boots",
            resultQuantity = 1,
            requirements = mapOf(
                "diamond_boots" to 1,
                "netherite_upgrade_smithing_template" to 1,
                "netherite_ingot" to 1
            ),
            recipeType = "smithing"
        ),
        Recipe(
            result = "cooked_porkchop",
            resultQuantity = 1,
            requirements = mapOf(
                "porkchop" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "painting",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 8,
                "white_wool" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_apple",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 8,
                "apple" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "oak_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "spruce_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "birch_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "jungle_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "acacia_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "dark_oak_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "crimson_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "warped_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bucket",
            resultQuantity = 3,
            requirements = mapOf(
                "iron_ingot" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brick",
            resultQuantity = 1,
            requirements = mapOf(
                "clay_ball" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "dried_kelp_block",
            resultQuantity = 1,
            requirements = mapOf(
                "dried_kelp" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "paper",
            resultQuantity = 3,
            requirements = mapOf(
                "sugar_cane" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "book",
            resultQuantity = 1,
            requirements = mapOf(
                "paper" to 3,
                "leather" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "compass",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 4,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "fishing_rod",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 3,
                "string" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "clock",
            resultQuantity = 1,
            requirements = mapOf(
                "gold_ingot" to 4,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spyglass",
            resultQuantity = 1,
            requirements = mapOf(
                "amethyst_shard" to 1,
                "copper_ingot" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cooked_cod",
            resultQuantity = 1,
            requirements = mapOf(
                "cod" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "cooked_salmon",
            resultQuantity = 1,
            requirements = mapOf(
                "salmon" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "white_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "bone_meal" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "orange_tulip" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "allium" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "blue_orchid" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "dandelion" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "sea_pickle" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "pink_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "pink_tulip" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_dye",
            resultQuantity = 2,
            requirements = mapOf(
                "white_dye" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "azure_bluet" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_dye",
            resultQuantity = 2,
            requirements = mapOf(
                "green_dye" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_dye",
            resultQuantity = 2,
            requirements = mapOf(
                "blue_dye" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "cornflower" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "cocoa_beans" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "cactus" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "red_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "poppy" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_dye",
            resultQuantity = 1,
            requirements = mapOf(
                "ink_sac" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bone_meal",
            resultQuantity = 1,
            requirements = mapOf(
                "bone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "sugar",
            resultQuantity = 1,
            requirements = mapOf(
                "sugar_cane" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cake",
            resultQuantity = 1,
            requirements = mapOf(
                "wheat" to 3,
                "sugar" to 2,
                "milk_bucket" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 3,
                "oak_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_bed",
            resultQuantity = 1,
            requirements = mapOf(
                "white_bed" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cookie",
            resultQuantity = 8,
            requirements = mapOf(
                "wheat" to 2,
                "cocoa_beans" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "shears",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dried_kelp",
            resultQuantity = 1,
            requirements = mapOf(
                "kelp" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "pumpkin_seeds",
            resultQuantity = 4,
            requirements = mapOf(
                "pumpkin" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "melon_seeds",
            resultQuantity = 1,
            requirements = mapOf(
                "melon_slice" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cooked_beef",
            resultQuantity = 1,
            requirements = mapOf(
                "beef" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "cooked_chicken",
            resultQuantity = 1,
            requirements = mapOf(
                "chicken" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "gold_nugget",
            resultQuantity = 9,
            requirements = mapOf(
                "gold_ingot" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "glass_bottle",
            resultQuantity = 3,
            requirements = mapOf(
                "glass" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "fermented_spider_eye",
            resultQuantity = 1,
            requirements = mapOf(
                "spider_eye" to 1,
                "brown_mushroom" to 1,
                "sugar" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blaze_powder",
            resultQuantity = 2,
            requirements = mapOf(
                "blaze_rod" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magma_cream",
            resultQuantity = 1,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slime_ball" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brewing_stand",
            resultQuantity = 1,
            requirements = mapOf(
                "cobblestone" to 3,
                "blaze_rod" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cauldron",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "ender_eye",
            resultQuantity = 1,
            requirements = mapOf(
                "ender_pearl" to 1,
                "blaze_powder" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "glistering_melon_slice",
            resultQuantity = 1,
            requirements = mapOf(
                "melon_slice" to 1,
                "gold_nugget" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "fire_charge",
            resultQuantity = 3,
            requirements = mapOf(
                "coal" to 1,
                "blaze_powder" to 1,
                "gunpowder" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "writable_book",
            resultQuantity = 1,
            requirements = mapOf(
                "book" to 1,
                "feather" to 1,
                "ink_sac" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "item_frame",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 8,
                "leather" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "glow_item_frame",
            resultQuantity = 1,
            requirements = mapOf(
                "item_frame" to 1,
                "glow_ink_sac" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "flower_pot",
            resultQuantity = 1,
            requirements = mapOf(
                "brick" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "baked_potato",
            resultQuantity = 1,
            requirements = mapOf(
                "potato" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "map",
            resultQuantity = 1,
            requirements = mapOf(
                "paper" to 8,
                "compass" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "golden_carrot",
            resultQuantity = 1,
            requirements = mapOf(
                "carrot" to 1,
                "gold_nugget" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pumpkin_pie",
            resultQuantity = 1,
            requirements = mapOf(
                "pumpkin" to 1,
                "sugar" to 1,
                "egg" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "firework_rocket",
            resultQuantity = 3,
            requirements = mapOf(
                "gunpowder" to 1,
                "paper" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "nether_brick",
            resultQuantity = 1,
            requirements = mapOf(
                "netherrack" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "cooked_rabbit",
            resultQuantity = 1,
            requirements = mapOf(
                "rabbit" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "rabbit_stew",
            resultQuantity = 1,
            requirements = mapOf(
                "bowl" to 1,
                "carrot" to 1,
                "baked_potato" to 1,
                "brown_mushroom" to 1,
                "cooked_rabbit" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "armor_stand",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 6,
                "smooth_stone_slab" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lead",
            resultQuantity = 1,
            requirements = mapOf(
                "string" to 4,
                "slime_ball" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cooked_mutton",
            resultQuantity = 1,
            requirements = mapOf(
                "mutton" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "white_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "orange_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "magenta_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "light_blue_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "yellow_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "lime_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "pink_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "gray_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "light_gray_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "cyan_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "purple_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "blue_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "brown_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "green_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "red_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_banner",
            resultQuantity = 1,
            requirements = mapOf(
                "black_wool" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "end_crystal",
            resultQuantity = 1,
            requirements = mapOf(
                "glass" to 7,
                "ender_eye" to 1,
                "ghast_tear" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "popped_chorus_fruit",
            resultQuantity = 1,
            requirements = mapOf(
                "chorus_fruit" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "beetroot_soup",
            resultQuantity = 1,
            requirements = mapOf(
                "beetroot" to 6,
                "bowl" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spectral_arrow",
            resultQuantity = 2,
            requirements = mapOf(
                "glowstone_dust" to 4,
                "arrow" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "shield",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 6,
                "iron_ingot" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "iron_ingot",
            resultQuantity = 9,
            requirements = mapOf(
                "iron_ingot" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crossbow",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 3,
                "iron_ingot" to 1,
                "string" to 2,
                "tripwire_hook" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "loom",
            resultQuantity = 1,
            requirements = mapOf(
                "string" to 2,
                "oak_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "composter",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_slab" to 7
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "barrel",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 6,
                "oak_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "smoker",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_log" to 4,
                "furnace" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blast_furnace",
            resultQuantity = 1,
            requirements = mapOf(
                "smooth_stone" to 3,
                "iron_ingot" to 5,
                "furnace" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cartography_table",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 4,
                "paper" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "fletching_table",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 4,
                "flint" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "grindstone",
            resultQuantity = 1,
            requirements = mapOf(
                "stick" to 2,
                "oak_planks" to 2,
                "stone_slab" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "smithing_table",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 4,
                "iron_ingot" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stonecutter",
            resultQuantity = 1,
            requirements = mapOf(
                "stone" to 3,
                "iron_ingot" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lantern",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_nugget" to 8,
                "torch" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "soul_lantern",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_nugget" to 8,
                "soul_torch" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "campfire",
            resultQuantity = 1,
            requirements = mapOf(
                "coal" to 1,
                "stick" to 3,
                "oak_log" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "soul_campfire",
            resultQuantity = 1,
            requirements = mapOf(
                "soul_sand" to 1,
                "stick" to 3,
                "oak_log" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "beehive",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 6,
                "honeycomb" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "honeycomb_block",
            resultQuantity = 1,
            requirements = mapOf(
                "honeycomb" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lodestone",
            resultQuantity = 1,
            requirements = mapOf(
                "netherite_ingot" to 1,
                "chiseled_stone_bricks" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blackstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "blackstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_polished_blackstone",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_blackstone_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "blackstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cracked_polished_blackstone_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_blackstone_bricks" to 1
            ),
            recipeType = "smelting"
        ),
        Recipe(
            result = "respawn_anchor",
            resultQuantity = 1,
            requirements = mapOf(
                "glowstone" to 3,
                "crying_obsidian" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "candle",
            resultQuantity = 1,
            requirements = mapOf(
                "string" to 1,
                "honeycomb" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "white_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "white_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "orange_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "orange_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "magenta_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "magenta_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_blue_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "yellow_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "yellow_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "lime_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "lime_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "pink_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "pink_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "gray_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "light_gray_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cyan_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "cyan_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "purple_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "purple_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "blue_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brown_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "brown_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "green_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "green_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "red_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "black_candle",
            resultQuantity = 1,
            requirements = mapOf(
                "candle" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "acacia_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "birch_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "dark_oak_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "jungle_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_button",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_door",
            resultQuantity = 3,
            requirements = mapOf(
                "mangrove_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "mangrove_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_planks",
            resultQuantity = 4,
            requirements = mapOf(
                "mangrove_log" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "mangrove_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "mangrove_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "mangrove_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "mangrove_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "mangrove_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mud_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "mud_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mud_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "mud_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mud_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "mud_bricks" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "mud_bricks",
            resultQuantity = 4,
            requirements = mapOf(
                "packed_mud" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "muddy_mangrove_roots",
            resultQuantity = 1,
            requirements = mapOf(
                "mud" to 1,
                "mangrove_roots" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "music_disc_5",
            resultQuantity = 1,
            requirements = mapOf(
                "disc_fragment_5" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "packed_mud",
            resultQuantity = 1,
            requirements = mapOf(
                "wheat" to 1,
                "mud" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "recovery_compass",
            resultQuantity = 1,
            requirements = mapOf(
                "compass" to 1,
                "echo_shard" to 8
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "spruce_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stripped_mangrove_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_mangrove_log",
            resultQuantity = 1,
            requirements = mapOf(
                "mangrove_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "creeper_banner_pattern",
            resultQuantity = 1,
            requirements = mapOf(
                "paper" to 1,
                "creeper_head" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_mosaic",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_mosaic_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "bamboo_mosaic" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_mosaic_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "bamboo_mosaic" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_planks",
            resultQuantity = 2,
            requirements = mapOf(
                "bamboo_block" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_block",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo" to 9
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stripped_bamboo_block",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_block" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_button",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_door",
            resultQuantity = 3,
            requirements = mapOf(
                "bamboo_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "bamboo_planks" to 4,
                "chest" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "bamboo_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "bamboo_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "bamboo_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "bamboo_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_raft",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_chest_raft",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_raft" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "calibrated_sculk_sensor",
            resultQuantity = 1,
            requirements = mapOf(
                "amethyst_shard" to 3,
                "sculk_sensor" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stripped_cherry_log",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "cherry_wood",
            resultQuantity = 3,
            requirements = mapOf(
                "cherry_log" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "stripped_cherry_wood",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_wood" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "cherry_planks",
            resultQuantity = 4,
            requirements = mapOf(
                "cherry_log" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_button",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_planks" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_door",
            resultQuantity = 3,
            requirements = mapOf(
                "cherry_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_fence",
            resultQuantity = 3,
            requirements = mapOf(
                "cherry_planks" to 4,
                "stick" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_fence_gate",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_planks" to 2,
                "stick" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_pressure_plate",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_planks" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_sign",
            resultQuantity = 3,
            requirements = mapOf(
                "cherry_planks" to 6,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "cherry_planks" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "cherry_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "cherry_planks" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_planks" to 5
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_chest_boat",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_boat" to 1,
                "chest" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_bookshelf",
            resultQuantity = 1,
            requirements = mapOf(
                "oak_planks" to 6,
                "oak_slab" to 3
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "decorated_pot",
            resultQuantity = 1,
            requirements = mapOf(
                "danger_pottery_sherd" to 4
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_oak_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oak_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_oak_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "spruce_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_spruce_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "birch_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_birch_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "jungle_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_jungle_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "acacia_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_acacia_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "dark_oak_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_dark_oak_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mangrove_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_mangrove_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crimson_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_crimson_stem" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "warped_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_warped_stem" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "bamboo_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_bamboo_block" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "cherry_hanging_sign",
            resultQuantity = 6,
            requirements = mapOf(
                "stripped_cherry_log" to 6,
                "chain" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "brush",
            resultQuantity = 1,
            requirements = mapOf(
                "feather" to 1,
                "copper_ingot" to 1,
                "stick" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "copper_bulb",
            resultQuantity = 4,
            requirements = mapOf(
                "copper_block" to 3,
                "blaze_rod" to 1,
                "redstone" to 1
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "exposed_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_bulb" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_bulb" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_bulb" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "waxed_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_bulb" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_bulb" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_bulb" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_copper_bulb",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_copper_bulb" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "exposed_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "exposed_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "exposed_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "weathered_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "weathered_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "weathered_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "oxidized_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "oxidized_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "oxidized_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "waxed_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "waxed_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "waxed_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "waxed_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "waxed_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "chiseled_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "waxed_exposed_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "waxed_exposed_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "waxed_exposed_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "waxed_exposed_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_chiseled_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "waxed_weathered_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "waxed_weathered_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "waxed_weathered_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "waxed_weathered_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_chiseled_copper",
            resultQuantity = 4,
            requirements = mapOf(
                "waxed_oxidized_copper_block" to 1
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "waxed_oxidized_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "waxed_oxidized_cut_copper_slab" to 2
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "waxed_oxidized_chiseled_copper",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "copper_trapdoor",
            resultQuantity = 2,
            requirements = mapOf(
                "copper_ingot" to 6
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "exposed_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_trapdoor" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_trapdoor" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_trapdoor" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "waxed_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_trapdoor" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_trapdoor" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_trapdoor" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_copper_trapdoor" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "tuff_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "tuff" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "tuff" to 3,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_wall",
            resultQuantity = 6,
            requirements = mapOf(
                "tuff" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_tuff",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_tuff",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff_slab" to 2,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_tuff",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_tuff",
            resultQuantity = 4,
            requirements = mapOf(
                "tuff" to 4,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_tuff_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_tuff_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "polished_tuff" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "polished_tuff_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_tuff_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_tuff_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "polished_tuff_wall",
            resultQuantity = 6,
            requirements = mapOf(
                "polished_tuff" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_bricks",
            resultQuantity = 4,
            requirements = mapOf(
                "polished_tuff" to 4,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_stairs",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff_bricks" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_stairs",
            resultQuantity = 4,
            requirements = mapOf(
                "tuff_bricks" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_slab",
            resultQuantity = 2,
            requirements = mapOf(
                "tuff_bricks" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_slab",
            resultQuantity = 6,
            requirements = mapOf(
                "tuff_bricks" to 3,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "tuff_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_wall",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff_bricks" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "tuff_brick_wall",
            resultQuantity = 6,
            requirements = mapOf(
                "tuff_bricks" to 6,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "chiseled_tuff_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_tuff_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "polished_tuff" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_tuff_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff_bricks" to 1,
            ),
            recipeType = "cutting"
        ),
        Recipe(
            result = "chiseled_tuff_bricks",
            resultQuantity = 1,
            requirements = mapOf(
                "tuff_brick_wall" to 2,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "crafter",
            resultQuantity = 1,
            requirements = mapOf(
                "iron_ingot" to 5,
                "crafting_table" to 1,
                "redstone" to 2,
                "dropper" to 1,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "mace",
            resultQuantity = 1,
            requirements = mapOf(
                "heavy_core" to 1,
                "breeze_rod" to 1,
            ),
            recipeType = "crafting"
        ),
        Recipe(
            result = "wind_charge",
            resultQuantity = 4,
            requirements = mapOf(
                "breeze_rod" to 1,
            ),
            recipeType = "crafting"
        ),
    )
}