package com.fracta7.crafter.util

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

fun decomposeItems(
    itemsToAmounts: Map<Item, Int>,
    recipeRegistry: RecipeRegistry
): Map<Item, Int> {
    val rawMaterials = mutableMapOf<Item, Int>()

    fun addRawMaterial(item: Item, amount: Int) {
        rawMaterials[item] = rawMaterials.getOrDefault(item, 0) + amount
    }

    fun processItem(item: Item, amount: Int) {
        if (!item.craftable) {
            addRawMaterial(item, amount)
        } else {
            val recipes = recipeRegistry.getRecipesByResult(item)
            if (recipes.isNotEmpty()) {
                val recipe = recipes[0] // Assuming there's at least one recipe and using the first one.
                val multiplier = amount / recipe.resultQuantity
                for ((requiredItem, requiredAmount) in recipe.requirements) {
                    processItem(requiredItem, requiredAmount * multiplier)
                }
            } else {
                addRawMaterial(item, amount) // If no recipe is found, treat it as raw material.
            }
        }
    }


    for ((item, amount) in itemsToAmounts) {
        processItem(item, amount)
    }

    return rawMaterials
}

fun convertIdMapToItemMap(
    idToAmountMap: Map<String, Int>,
    itemRegistry: ItemRegistry
): Map<Item, Int> {
    val itemToAmountMap = mutableMapOf<Item, Int>()

    for ((id, amount) in idToAmountMap) {
        val item = itemRegistry.getItem(id)!!
        itemToAmountMap[item] = amount
    }

    return itemToAmountMap
}
