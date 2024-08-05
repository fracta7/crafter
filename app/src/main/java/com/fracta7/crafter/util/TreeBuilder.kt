package com.fracta7.crafter.util

import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeTreeNode

// Function to build the recipe tree with recipe type
fun buildRecipeTree(itemID: ItemID, quantity: Int, itemRegistry: ItemRegistry, recipeRegistry: RecipeRegistry): RecipeTreeNode {
    val item = itemRegistry.getItem(itemID)
    val recipes = recipeRegistry.getRecipesByResult(itemID)

    if (item == null || (recipes.isEmpty() && !item.craftable)) {
        // Return a leaf node if the item is not found or not craftable
        return RecipeTreeNode(itemID, quantity)
    }

    // Build the tree for each requirement
    val children = recipes.flatMap { recipe ->
        recipe.requirements.map { (requiredItemID, requiredQuantity) ->
            val neededQuantity = resourceAmount(recipe.resultQuantity, quantity) * requiredQuantity
            buildRecipeTree(requiredItemID, neededQuantity, itemRegistry, recipeRegistry)
        }
    }

    return RecipeTreeNode(itemID, quantity, recipes.firstOrNull()?.recipeType, children)
}