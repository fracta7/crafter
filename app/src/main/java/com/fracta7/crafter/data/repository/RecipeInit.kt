package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType

/**
 * Initiates all recipes
 * @param itemRegistry ItemRegistry instance to get all items.
 * @return List of Recipes.
 */
fun recipeInit(itemRegistry: ItemRegistry): List<Recipe> {
    return listOf(
        Recipe(
            result = itemRegistry.getItem("polished_granite")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("granite")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_diorite")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("diorite")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_andesite")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("andesite")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_deepslate")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cobbled_deepslate")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("coarse_dirt")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("gravel")!! to 2,
                itemRegistry.getItem("sand")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("oak_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("spruce_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("birch_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("jungle_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("acacia_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("dark_oak_log")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("crimson_stem")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(itemRegistry.getItem("warped_stem")!! to 1),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("coal_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("coal")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("raw_iron_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("raw_iron")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("raw_copper_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("raw_copper")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("raw_gold_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("raw_gold")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("amethyst_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("amethyst_shard")!! to 4),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("iron_ingot")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("copper_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_ingot")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gold_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("gold_ingot")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("diamond")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_block")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("netherite_ingot")!! to 9),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("exposed_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("weathered_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("oxidized_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("exposed_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("weathered_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("oxidized_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("exposed_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_stairs")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("weathered_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_stairs")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("oxidized_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_stairs")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("cut_copper_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(itemRegistry.getItem("copper_block")!! to 1),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("exposed_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_slab")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("weathered_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_slab")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("oxidized_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(itemRegistry.getItem("cut_copper_slab")!! to 1),
            recipeType = RecipeType.Oxidation
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_copper_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("copper_block")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_exposed_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("exposed_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_weathered_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("weathered_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_oxidized_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oxidized_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cut_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_exposed_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("exposed_cut_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_weathered_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("weathered_cut_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_oxidized_cut_copper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oxidized_cut_copper")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cut_copper_stairs")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_exposed_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("exposed_cut_copper_stairs")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_weathered_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("weathered_cut_copper_stairs")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_oxidized_cut_copper_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oxidized_cut_copper_stairs")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_exposed_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("exposed_cut_copper_slab")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_weathered_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("weathered_cut_copper_slab")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_oxidized_cut_copper_slab")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oxidized_cut_copper_slab")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_oak_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_spruce_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_birch_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_jungle_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_acacia_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_dark_oak_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_crimson_stem")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("crimson_stem")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_warped_stem")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_stem")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_oak_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_spruce_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_birch_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_jungle_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_acacia_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_dark_oak_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_crimson_hyphae")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("crimson_hyphae")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_warped_hyphae")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_hyphae")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("oak_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("oak_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("spruce_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("birch_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("jungle_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("acacia_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_hyphae")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("crimson_stem")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_hyphae")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("warped_stem")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("glass")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("tinted_glass")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 1,
                itemRegistry.getItem("amethyst_shard")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lapis_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("lapis_lazuli")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cut_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("light_blue_wool")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("light_gray_wool")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_dye")!! to 1,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_wool")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 1,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("moss_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("moss_block")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_stone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("smooth_stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cut_sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cobblestone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("quartz_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cut_red_sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("purpur_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("purpur_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("prismarine")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_prismarine_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("dark_prismarine")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_quartz")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_red_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_stone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("brick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bookshelf")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("book")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_cobblestone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 1,
                itemRegistry.getItem("vine")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("torch")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 1,
                itemRegistry.getItem("coal")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("end_rod")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("blaze_rod")!! to 1,
                itemRegistry.getItem("popped_chorus_fruit")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purpur_block")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("popped_chorus_fruit")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purpur_pillar")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("purpur_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("purpur_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("purpur_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chest")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crafting_table")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("furnace")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("ladder")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cobblestone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("snow_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("snowball")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jukebox")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 8,
                itemRegistry.getItem("diamond")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        )
    )
}