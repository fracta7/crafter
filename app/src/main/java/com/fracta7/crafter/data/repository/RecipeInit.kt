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
            result = itemRegistry.getItem("waxed_exposed_copper_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("exposed_copper_block")!! to 1,
                itemRegistry.getItem("honeycomb")!! to 1
            ),
            recipeType = RecipeType.Waxing
        ),
        Recipe(
            result = itemRegistry.getItem("waxed_weathered_copper_block")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("weathered_copper_block")!! to 1,
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
        )
    )
}