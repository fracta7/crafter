package com.fracta7.crafter.model

/**
 * Data class to represent recipes
 * @property result result of recipe
 * @property resultQuantity quantity of resulting item
 * @property requirements a map of required items mapped to their quantity.
 * @property recipeType defines the recipe type (crafting, smelting etc.).
 */
data class Recipe(
    val result: Item,
    val resultQuantity: Int,
    val requirements: Map<Item, Int>,
    val recipeType: RecipeType
)