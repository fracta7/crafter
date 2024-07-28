package com.fracta7.crafter.domain.model

/**
 * data class to represent different recipe types.
 * @property id represents the ID of recipe.
 * @property name represents the name of recipe.
 * @property item is the itemID of recipe representation
 */
data class RecipeType(
    val id: String,
    val name: String,
    val item: String
)
