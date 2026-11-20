package com.fracta7.crafter.domain.usecases

import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

/**
 * Decomposes a map of target items into raw materials and leftovers.
 * Delegates to [solveCraftingPlan].
 */
fun decomposeItems(
    itemsToDecompose: Map<ItemID, Int>,
    recipeRegistry: RecipeRegistry,
    itemRegistry: ItemRegistry
): Pair<Map<ItemID, Int>, Map<ItemID, Int>> {
    val plan = solveCraftingPlan(
        targets = itemsToDecompose,
        recipeRegistry = recipeRegistry,
        itemRegistry = itemRegistry
    )
    return plan.rawMaterials to plan.leftovers
}
