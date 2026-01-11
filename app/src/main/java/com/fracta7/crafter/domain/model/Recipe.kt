package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable

typealias ItemID = String

/**
 * Data class to represent recipes
 * @property result result of recipe
 * @property amount quantity of resulting item
 * @property requirements a map of required items mapped to their quantity.
 * @property recipeType defines the recipe type (crafting, smelting etc.).
 */
@Immutable
data class Recipe(
    val result: ItemID,
    val amount: Int,
    val requirements: Map<ItemID, Int>,
    val recipeType: RecipeTypeID,
    val custom: Boolean = false
)