package com.fracta7.crafter.domain.model

data class RecipeTreeNode(
    val itemID: ItemID,
    val quantity: Int,
    val recipeType: RecipeTypeID? = null, // Nullable in case no recipe type is available
    val children: List<RecipeTreeNode> = emptyList()
)