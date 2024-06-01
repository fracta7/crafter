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
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("carved_pumpkin")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pumpkin")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("jack_o_lantern")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("carved_pumpkin")!! to 1,
                itemRegistry.getItem("torch")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_basalt")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("basalt")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("soul_torch")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("coal")!! to 1,
                itemRegistry.getItem("stick")!! to 1,
                itemRegistry.getItem("soul_sand")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("glowstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glowstone_dust")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_stone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone_bricks")!! to 1,
                itemRegistry.getItem("vine")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cracked_stone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone_bricks")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_stone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cracked_deepslate_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("deepslate_bricks")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_tiles")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cracked_deepslate_tiles")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("deepslate_tiles")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_deepslate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_bars")!!,
            resultQuantity = 16,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chain")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("iron_nugget")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("melon")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("melon_slice")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_brick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cracked_nether_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_nether_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_brick_fence")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 4,
                itemRegistry.getItem("nether_brick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("enchanting_table")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 2,
                itemRegistry.getItem("book")!! to 1,
                itemRegistry.getItem("obsidian")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("end_stone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("end_stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("sandstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("ender_chest")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("obsidian")!! to 8,
                itemRegistry.getItem("ender_eye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("emerald_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("emerald")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("beacon")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 5,
                itemRegistry.getItem("obsidian")!! to 3,
                itemRegistry.getItem("nether_star")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cobblestone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_cobblestone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mossy_cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_sandstone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_stone_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mossy_stone_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("granite_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("granite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("andesite_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("andesite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_nether_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("sandstone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("end_stone_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("end_stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("diorite_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diorite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("blackstone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cobbled_deepslate_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_deepslate_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_tile_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("anvil")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 4,
                itemRegistry.getItem("iron_block")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_quartz_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("quartz_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("quartz_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("quartz_pillar")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("quartz_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("quartz_block")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("white_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_terracotta")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("terracotta")!! to 8,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("hay_block")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("wheat")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("orange_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("magenta_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("light_blue_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("yellow_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("lime_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("pink_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("gray_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("light_gray_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("cyan_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("purple_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("blue_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("brown_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("green_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("red_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_carpet")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("black_wool")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("packed_ice")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("ice")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_stained_glass")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 8,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_stained_glass_pane")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("glass_pane")!! to 8,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_shard")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_shard")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_prismarine")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_shard")!! to 8,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("prismarine_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_prismarine_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_prismarine")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("sea_lantern")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("prismarine_shard")!! to 4,
                itemRegistry.getItem("prismarine_crystals")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sand")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_red_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cut_red_sandstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_sandstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("magma_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("magma_cream")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_wart_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_wart")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_nether_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("nether_brick")!! to 2,
                itemRegistry.getItem("nether_wart")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bone_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bone_meal")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_shell")!! to 2,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_shulker_box")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("shulker_box")!! to 1,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("orange_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("magenta_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_blue_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("yellow_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("lime_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pink_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gray_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_gray_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cyan_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("purple_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blue_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("brown_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("green_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("green_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("red_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("black_glazed_terracotta")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("black_terracotta")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("white_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("orange_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("orange_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("magenta_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_blue_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("yellow_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("lime_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("lime_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("pink_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pink_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("gray_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gray_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_gray_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cyan_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("purple_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("purple_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("blue_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blue_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("brown_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("brown_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("green_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("green_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("red_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("black_concrete")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("black_concrete_powder")!! to 1
            ),
            recipeType = RecipeType.Watering
        ),
        Recipe(
            result = itemRegistry.getItem("white_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_concrete_powder")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gravel")!! to 4,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_ice")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("packed_ice")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("conduit")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("heart_of_the_sea")!! to 1,
                itemRegistry.getItem("nautilus_shell")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_granite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("granite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_red_sandstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("smooth_red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_stone_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mossy_stone_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_diorite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diorite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_cobblestone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mossy_cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("end_stone_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("end_stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_sandstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("smooth_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_quartz_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("smooth_quartz")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("granite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("granite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("andesite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("andesite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_nether_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_andesite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("andesite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("diorite_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diorite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cobbled_deepslate_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_deepslate_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_tile_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_granite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("granite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_red_sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("smooth_red_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_stone_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("mossy_stone_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_diorite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("diorite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mossy_cobblestone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("mossy_cobblestone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("end_stone_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("end_stone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_sandstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("smooth_sandstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("smooth_quartz_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("smooth_quartz")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("granite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("granite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("andesite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("andesite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("red_nether_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("red_nether_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_andesite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("andesite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("diorite_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("diorite")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("cobbled_deepslate_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_deepslate_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("deepslate_tile_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cobbled_deepslate")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("scaffolding")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("bamboo")!! to 6,
                itemRegistry.getItem("string")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("redstone_torch")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("redstone")!! to 1,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("redstone_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("redstone")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("repeater")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("redstone_torch")!! to 2,
                itemRegistry.getItem("redstone")!! to 1,
                itemRegistry.getItem("stone")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("comparator")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("redstone_torch")!! to 3,
                itemRegistry.getItem("quartz")!! to 1,
                itemRegistry.getItem("stone")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("piston")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 3,
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("cobblestone")!! to 4,
                itemRegistry.getItem("redstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("sticky_piston")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("piston")!! to 1,
                itemRegistry.getItem("slime_ball")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("slime_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("slime_ball")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("honey_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("honey_bottle")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("observer")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 6,
                itemRegistry.getItem("redstone")!! to 2,
                itemRegistry.getItem("quartz")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("hopper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 5,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dispenser")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 7,
                itemRegistry.getItem("redstone")!! to 1,
                itemRegistry.getItem("bow")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dropper")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 7,
                itemRegistry.getItem("redstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lectern")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_slab")!! to 4,
                itemRegistry.getItem("bookshelf")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("target")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("redstone")!! to 4,
                itemRegistry.getItem("hay_block")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lever")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 1,
                itemRegistry.getItem("cobblestone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lightning_rod")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("copper_ingot")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("daylight_detector")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 3,
                itemRegistry.getItem("quartz")!! to 3,
                itemRegistry.getItem("oak_slab")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("tripwire_hook")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("stick")!! to 1,
                itemRegistry.getItem("oak_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("trapped_chest")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("chest")!! to 1,
                itemRegistry.getItem("tripwire_hook")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("tnt")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sand")!! to 4,
                itemRegistry.getItem("gunpowder")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("redstone_lamp")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glowstone")!! to 1,
                itemRegistry.getItem("redstone")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("note_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 8,
                itemRegistry.getItem("redstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("polished_blackstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("polished_blackstone")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_weighted_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("heavy_weighted_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_trapdoor")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("powered_rail")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 6,
                itemRegistry.getItem("redstone")!! to 1,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("detector_rail")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 6,
                itemRegistry.getItem("redstone")!! to 1,
                itemRegistry.getItem("stone_pressure_plate")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("rail")!!,
            resultQuantity = 16,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("activator_rail")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 6,
                itemRegistry.getItem("stick")!! to 2,
                itemRegistry.getItem("redstone_torch")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("minecart")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chest_minecart")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("minecart")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("furnace_minecart")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("minecart")!! to 1,
                itemRegistry.getItem("furnace")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("tnt_minecart")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("minecart")!! to 1,
                itemRegistry.getItem("tnt")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("hopper_minecart")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("minecart")!! to 1,
                itemRegistry.getItem("hopper")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("carrot_on_a_stick")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("carrot")!! to 1,
                itemRegistry.getItem("fishing_rod")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_fungus_on_a_stick")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("warped_fungus")!! to 1,
                itemRegistry.getItem("fishing_rod")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("turtle_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("scute")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("flint_and_steel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("flint")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bow")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 3,
                itemRegistry.getItem("string")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("arrow")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 1,
                itemRegistry.getItem("flint")!! to 1,
                itemRegistry.getItem("feather")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("charcoal")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_log")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_ingot")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 4,
                itemRegistry.getItem("netherite_scrap")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_scrap")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("ancient_debris")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("wooden_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("wooden_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 1,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("wooden_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("wooden_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("wooden_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 1,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stone_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 2,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 1,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 2,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 2,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 1,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 3,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 2,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_sword")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_shovel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_shovel")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_pickaxe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_pickaxe")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_axe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_axe")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_hoe")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_hoe")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("stick")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bowl")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mushroom_stew")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bowl")!! to 1,
                itemRegistry.getItem("brown_mushroom")!! to 1,
                itemRegistry.getItem("red_mushroom")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bread")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("wheat")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("leather_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("leather")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("leather_chestplate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("leather")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("leather_leggings")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("leather")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("leather_boots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("leather")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_chestplate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_leggings")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_boots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_chestplate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_leggings")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_boots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_chestplate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_leggings")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("diamond_boots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_helmet")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_helmet")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_chestplate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_chestplate")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_leggings")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_leggings")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("netherite_boots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("diamond_boots")!! to 1,
                itemRegistry.getItem("netherite_upgrade_smithing_template")!! to 1,
                itemRegistry.getItem("netherite_ingot")!! to 1
            ),
            recipeType = RecipeType.Smithing
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_porkchop")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("porkchop")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("painting")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 8,
                itemRegistry.getItem("white_wool")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_apple")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 8,
                itemRegistry.getItem("apple")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("spruce_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("birch_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("jungle_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("acacia_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("crimson_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("warped_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bucket")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brick")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("clay_ball")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("dried_kelp_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dried_kelp")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("paper")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("sugar_cane")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("book")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("paper")!! to 3,
                itemRegistry.getItem("leather")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("compass")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 4,
                itemRegistry.getItem("redstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("fishing_rod")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 3,
                itemRegistry.getItem("string")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("clock")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 4,
                itemRegistry.getItem("redstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spyglass")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("amethyst_shard")!! to 1,
                itemRegistry.getItem("copper_ingot")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_cod")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cod")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_salmon")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("salmon")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("white_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bone_meal")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("orange_tulip")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("allium")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blue_orchid")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dandelion")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sea_pickle")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pink_tulip")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_dye")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("white_dye")!! to 1,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("azure_bluet")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_dye")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("green_dye")!! to 1,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_dye")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("blue_dye")!! to 1,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cornflower")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cocoa_beans")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cactus")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("red_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("poppy")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_dye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("ink_sac")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bone_meal")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("sugar")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("sugar_cane")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cake")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("wheat")!! to 3,
                itemRegistry.getItem("sugar")!! to 2,
                itemRegistry.getItem("milk_bucket")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 3,
                itemRegistry.getItem("oak_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_bed")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_bed")!! to 1,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cookie")!!,
            resultQuantity = 8,
            requirements = mapOf(
                itemRegistry.getItem("wheat")!! to 2,
                itemRegistry.getItem("cocoa_beans")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("shears")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dried_kelp")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("kelp")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("pumpkin_seeds")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("pumpkin")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("melon_seeds")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("melon_slice")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_beef")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("beef")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_chicken")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("chicken")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("gold_nugget")!!,
            resultQuantity = 9,
            requirements = mapOf(
                itemRegistry.getItem("gold_ingot")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("glass_bottle")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("fermented_spider_eye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spider_eye")!! to 1,
                itemRegistry.getItem("brown_mushroom")!! to 1,
                itemRegistry.getItem("sugar")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blaze_powder")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("blaze_rod")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magma_cream")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blaze_powder")!! to 1,
                itemRegistry.getItem("slime_ball")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brewing_stand")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cobblestone")!! to 3,
                itemRegistry.getItem("blaze_rod")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cauldron")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("ender_eye")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("ender_pearl")!! to 1,
                itemRegistry.getItem("blaze_powder")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("glistering_melon_slice")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("melon_slice")!! to 1,
                itemRegistry.getItem("gold_nugget")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("fire_charge")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("coal")!! to 1,
                itemRegistry.getItem("blaze_powder")!! to 1,
                itemRegistry.getItem("gunpowder")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("writable_book")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("book")!! to 1,
                itemRegistry.getItem("feather")!! to 1,
                itemRegistry.getItem("ink_sac")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("item_frame")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 8,
                itemRegistry.getItem("leather")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("glow_item_frame")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("item_frame")!! to 1,
                itemRegistry.getItem("glow_ink_sac")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("flower_pot")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("brick")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("baked_potato")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("potato")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("map")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("paper")!! to 8,
                itemRegistry.getItem("compass")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("golden_carrot")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("carrot")!! to 1,
                itemRegistry.getItem("gold_nugget")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pumpkin_pie")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pumpkin")!! to 1,
                itemRegistry.getItem("sugar")!! to 1,
                itemRegistry.getItem("egg")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("firework_rocket")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("gunpowder")!! to 1,
                itemRegistry.getItem("paper")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("nether_brick")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("netherrack")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_rabbit")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("rabbit")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("rabbit_stew")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bowl")!! to 1,
                itemRegistry.getItem("carrot")!! to 1,
                itemRegistry.getItem("baked_potato")!! to 1,
                itemRegistry.getItem("brown_mushroom")!! to 1,
                itemRegistry.getItem("cooked_rabbit")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("armor_stand")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 6,
                itemRegistry.getItem("smooth_stone_slab")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lead")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("string")!! to 4,
                itemRegistry.getItem("slime_ball")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cooked_mutton")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mutton")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("white_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("orange_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("magenta_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_blue_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("yellow_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("lime_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("pink_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("gray_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("light_gray_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cyan_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("purple_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blue_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("brown_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("green_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("red_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_banner")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("black_wool")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("end_crystal")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glass")!! to 7,
                itemRegistry.getItem("ender_eye")!! to 1,
                itemRegistry.getItem("ghast_tear")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("popped_chorus_fruit")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("chorus_fruit")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("beetroot_soup")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("beetroot")!! to 6,
                itemRegistry.getItem("bowl")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spectral_arrow")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("glowstone_dust")!! to 4,
                itemRegistry.getItem("arrow")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("shield")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("iron_ingot")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("iron_ingot")!!,
            resultQuantity = 9,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crossbow")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 3,
                itemRegistry.getItem("iron_ingot")!! to 1,
                itemRegistry.getItem("string")!! to 2,
                itemRegistry.getItem("tripwire_hook")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("loom")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("string")!! to 2,
                itemRegistry.getItem("oak_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("composter")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_slab")!! to 7
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("barrel")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("oak_slab")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("smoker")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_log")!! to 4,
                itemRegistry.getItem("furnace")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blast_furnace")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("smooth_stone")!! to 3,
                itemRegistry.getItem("iron_ingot")!! to 5,
                itemRegistry.getItem("furnace")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cartography_table")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 4,
                itemRegistry.getItem("paper")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("fletching_table")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 4,
                itemRegistry.getItem("flint")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("grindstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stick")!! to 2,
                itemRegistry.getItem("oak_planks")!! to 2,
                itemRegistry.getItem("stone_slab")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("smithing_table")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 4,
                itemRegistry.getItem("iron_ingot")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stonecutter")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("stone")!! to 3,
                itemRegistry.getItem("iron_ingot")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lantern")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_nugget")!! to 8,
                itemRegistry.getItem("torch")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("soul_lantern")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_nugget")!! to 8,
                itemRegistry.getItem("soul_torch")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("campfire")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("coal")!! to 1,
                itemRegistry.getItem("stick")!! to 3,
                itemRegistry.getItem("oak_log")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("soul_campfire")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("soul_sand")!! to 1,
                itemRegistry.getItem("stick")!! to 3,
                itemRegistry.getItem("oak_log")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("beehive")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("honeycomb")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("honeycomb_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("honeycomb")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lodestone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("netherite_ingot")!! to 1,
                itemRegistry.getItem("chiseled_stone_bricks")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blackstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("blackstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_polished_blackstone")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("polished_blackstone_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("blackstone")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cracked_polished_blackstone_bricks")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("polished_blackstone_bricks")!! to 1
            ),
            recipeType = RecipeType.Smelting
        ),
        Recipe(
            result = itemRegistry.getItem("respawn_anchor")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("glowstone")!! to 3,
                itemRegistry.getItem("crying_obsidian")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("string")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("white_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("white_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("orange_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("orange_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("magenta_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("magenta_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_blue_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("light_blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("yellow_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("yellow_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("lime_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("lime_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("pink_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("pink_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("gray_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("light_gray_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("light_gray_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cyan_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("cyan_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("purple_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("purple_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("blue_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("blue_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brown_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("brown_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("green_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("green_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("red_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("red_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("black_candle")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("candle")!! to 1,
                itemRegistry.getItem("black_dye")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("acacia_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("birch_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("dark_oak_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("jungle_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_log")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mud_brick_slab")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("mud_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mud_brick_stairs")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mud_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mud_brick_wall")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mud_bricks")!! to 1
            ),
            recipeType = RecipeType.Cutting
        ),
        Recipe(
            result = itemRegistry.getItem("mud_bricks")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("packed_mud")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("muddy_mangrove_roots")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mud")!! to 1,
                itemRegistry.getItem("mangrove_roots")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("music_disc_5")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("disc_fragment_5")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("packed_mud")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("wheat")!! to 1,
                itemRegistry.getItem("mud")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("recovery_compass")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("compass")!! to 1,
                itemRegistry.getItem("echo_shard")!! to 8
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("spruce_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_mangrove_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_mangrove_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("mangrove_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("creeper_banner_pattern")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("paper")!! to 1,
                itemRegistry.getItem("creeper_head")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_mosaic")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_slab")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_mosaic_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_mosaic")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_mosaic_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_mosaic")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_planks")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_block")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo")!! to 9
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_bamboo_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_block")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 4,
                itemRegistry.getItem("chest")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_raft")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_chest_raft")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("bamboo_raft")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("calibrated_sculk_sensor")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("amethyst_shard")!! to 3,
                itemRegistry.getItem("sculk_sensor")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_cherry_log")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_log")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_wood")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("cherry_log")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("stripped_cherry_wood")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_wood")!! to 1
            ),
            recipeType = RecipeType.Stripping
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_planks")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("cherry_log")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_button")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_door")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_fence")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 4,
                itemRegistry.getItem("stick")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_fence_gate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 2,
                itemRegistry.getItem("stick")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_pressure_plate")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_sign")!!,
            resultQuantity = 3,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 6,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_slab")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_stairs")!!,
            resultQuantity = 4,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_trapdoor")!!,
            resultQuantity = 2,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 6
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_planks")!! to 5
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_chest_boat")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("cherry_boat")!! to 1,
                itemRegistry.getItem("chest")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("chiseled_bookshelf")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("oak_planks")!! to 6,
                itemRegistry.getItem("oak_slab")!! to 3
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("decorated_pot")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("danger_pottery_sherd")!! to 4
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_oak_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("oak_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_oak_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("spruce_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_spruce_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("birch_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_birch_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("jungle_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_jungle_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("acacia_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_acacia_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("dark_oak_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_dark_oak_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("mangrove_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_mangrove_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("crimson_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_crimson_stem")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("warped_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_warped_stem")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("bamboo_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_bamboo_block")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("cherry_hanging_sign")!!,
            resultQuantity = 6,
            requirements = mapOf(
                itemRegistry.getItem("stripped_cherry_log")!! to 6,
                itemRegistry.getItem("chain")!! to 2
            ),
            recipeType = RecipeType.Crafting
        ),
        Recipe(
            result = itemRegistry.getItem("brush")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("feather")!! to 1,
                itemRegistry.getItem("copper_ingot")!! to 1,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        ),
    )
}