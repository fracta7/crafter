package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.Recipe

fun specialRecipesInit(): List<Recipe> {
    return listOf(
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
                "exposed_copper_trapdoor" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_copper_trapdoor",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_trapdoor" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "exposed_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_grate" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "exposed_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_door" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_grate" to 1
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "weathered_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_door" to 1
            ),
            recipeType = "oxidation"
        ),

        Recipe(
            result = "oxidized_copper_grate",
            resultQuantity = 4,
            requirements = mapOf(
                "weathered_copper" to 4
            ),
            recipeType = "oxidation"
        ),
        Recipe(
            result = "oxidized_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_door" to 1
            ),
            recipeType = "oxidation"
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
            result = "carved_pumpkin",
            resultQuantity = 1,
            requirements = mapOf(
                "pumpkin" to 1
            ),
            recipeType = "stripping"
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
            result = "stripped_cherry_log",
            resultQuantity = 1,
            requirements = mapOf(
                "cherry_log" to 1
            ),
            recipeType = "stripping"
        ),
        Recipe(
            result = "stripped_bamboo_block",
            resultQuantity = 1,
            requirements = mapOf(
                "bamboo_block" to 1
            ),
            recipeType = "stripping"
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
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_chiseled_copper" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
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
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
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
            result = "waxed_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_grate" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_grate" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_cut_copper_slab",
            resultQuantity = 1,
            requirements = mapOf(
                "cut_copper_slab" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "copper_door" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_exposed_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "exposed_copper_grate" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_grate" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_weathered_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "weathered_copper_door" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_copper_grate",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_copper_grate" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
        ),
        Recipe(
            result = "waxed_oxidized_copper_door",
            resultQuantity = 1,
            requirements = mapOf(
                "oxidized_copper_door" to 1,
                "honeycomb" to 1
            ),
            recipeType = "waxing"
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
            result = "fuel",
            resultQuantity = 8,
            requirements = mapOf(
                "coal" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "fuel",
            resultQuantity = 8,
            requirements = mapOf(
                "charcoal" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "fuel",
            resultQuantity = 12,
            requirements = mapOf(
                "blaze_rod" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "fuel",
            resultQuantity = 20,
            requirements = mapOf(
                "dried_kelp_block" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "fuel",
            resultQuantity = 80,
            requirements = mapOf(
                "coal_block" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "fuel",
            resultQuantity = 100,
            requirements = mapOf(
                "lava_bucket" to 1
            ),
            recipeType = "fuel"
        ),
        Recipe(
            result = "white_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "white_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "light_gray_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "light_gray_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "gray_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "gray_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "black_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "black_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "brown_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "brown_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "red_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "red_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "orange_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "orange_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "yellow_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "yellow_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "lime_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "lime_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "green_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "green_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "cyan_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "cyan_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "light_blue_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "light_blue_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "blue_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "blue_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "purple_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "purple_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "magenta_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "magenta_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "pink_shulker_box",
            resultQuantity = 1,
            requirements = mapOf(
                "shulker_box" to 1,
                "pink_dye" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "suspicious_stew",
            resultQuantity = 1,
            requirements = mapOf(
                "red_mushroom" to 1,
                "brown_mushroom" to 1,
                "poppy" to 1,
                "bowl" to 1,
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "enchanted_book",
            resultQuantity = 1,
            requirements = mapOf(
                "paper" to 3,
                "leather" to 1
            ),
            recipeType = "crafting_shapeless"
        ),
        Recipe(
            result = "awkward_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_bottle" to 3,
                "nether_wart" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "weakness_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_bottle" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "weakness_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "weakness_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "sugar" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "swiftness_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "swiftness_splash_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slowness_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slowness_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "rabbit_foot" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "leaping_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "leaping_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 2,
                "awkward_potion" to 3,
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "strength_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "strength_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "glistering_melon_slice" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "healing_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_splash_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_splash_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_lingering_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_lingering_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "harming_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "healing_lingering_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "harming_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_lingering_potion_2" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "poison_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "poison_lingering_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "ghast_tear" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "regeneration_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "regeneration_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "turtle_helmet" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_potion_2" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_splash_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_splash_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_splash_potion_2" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_lingering_potion_2",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_lingering_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "turtle_master_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "turtle_master_lingering_potion" to 3,
                "glowstone_dust" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "magma_cream" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "fire_resistance_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "fire_resistance_lingering_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "golden_carrot" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "water_breathing_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "water_breathing_lingering_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "invisibility_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_splash_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_splash_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "invisibility_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_lingering_potion" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "invisibility_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "night_vision_lingering_potion_e" to 3,
                "fermented_spider_eye" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "invisibility_lingering_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "invisibility_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "invisibility_splash_potion_e" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "phantom_membrane" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_splash_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_splash_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_potion_e" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "slow_falling_lingering_potion_e",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "slow_falling_lingering_potion" to 3,
                "redstone" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "wind_charging_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "breeze_rod" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "wind_charging_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "wind_charging_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "wind_charging_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "wind_charging_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "weaving_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "cobweb" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "weaving_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "weaving_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "weaving_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "weaving_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "oozing_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "awkward_potion" to 3,
                "slime_block" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "oozing_splash_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "oozing_potion" to 3,
                "gunpowder" to 1
            ),
            recipeType = "brewing"
        ),
        Recipe(
            result = "oozing_lingering_potion",
            resultQuantity = 3,
            requirements = mapOf(
                "blaze_powder" to 1,
                "oozing_splash_potion" to 3,
                "dragon_breath" to 1
            ),
            recipeType = "brewing"
        ),
    )
}