package com.fracta7.crafter.model

/**
 * Initiates all recipes
 * @param itemRegistry ItemRegistry instance to get all items.
 * @return List of Recipes.
 */
fun recipeInit(itemRegistry: ItemRegistry): List<Recipe> {
    return listOf(
        Recipe(
            result = itemRegistry.getItem("iron_sword")!!,
            resultQuantity = 1,
            requirements = mapOf(
                itemRegistry.getItem("iron_ingot")!! to 2,
                itemRegistry.getItem("stick")!! to 1
            ),
            recipeType = RecipeType.Crafting
        )
    )
}