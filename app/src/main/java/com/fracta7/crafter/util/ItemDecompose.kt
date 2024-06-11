package com.fracta7.crafter.util

import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

/**
 * Function that will decompose a list of items to raw items with left-overs.
 * @param itemsToDecompose a map of items to be decomposed.
 * @param recipeRegistry a recipe registry to pull recipes.
 * @return returns a pair of raw materials mapped to left-overs. First - raw material Map<Item, Int>, second - left-overs Map<Item, Int>.
 */
fun decomposeItems(
    itemsToDecompose: Map<Item, Int>,
    recipeRegistry: RecipeRegistry
): Pair<Map<Item, Int>, Map<Item, Int>> {
    val rawMaterials = mutableMapOf<Item, Int>() //main raw material list
    val leftOvers = mutableMapOf<Item, Int>() //internal list of leftovers
    val rawMaterialsLeftOvers = mutableMapOf<Item, Int>() //processed list of leftovers

    fun addRawMaterial(item: Item, amount: Int) {
        rawMaterials[item] = rawMaterials.getOrDefault(item, 0) + amount
    }

    fun addRawMaterialLeftOver(item: Item, amount: Int) {
        rawMaterialsLeftOvers[item] = rawMaterialsLeftOvers.getOrDefault(item, 0) + amount
    }

    fun addLeftOver(item: Item, amount: Int) {
        leftOvers[item] = leftOvers.getOrDefault(item, 0) + amount
    }

    /**
     * Recursive function that will decompose a list of items
     * @param item item to be decomposed
     * @param amount amount of items to be decomposed
     * @param isLeftOver boolean to indicated if certain functionality is used for decomposing leftovers
     * @param action a function that will keep track of decomposed items
     */
    fun processItem(item: Item, amount: Int, isLeftOver: Boolean, action: (Item, Int) -> Unit) {
        if (!item.craftable) {
            action(item, amount)
        } else {
            val recipes = recipeRegistry.getRecipesByResult(item)
            val recipe = recipes[0] // Assuming there's at least one recipe and using the first one.
            val multiplier = resourceAmount(
                result = recipe.resultQuantity,
                amountNeeded = amount
            )//amount / recipe.resultQuantity
            val leftOver =
                if (amount < recipe.resultQuantity) recipe.resultQuantity - amount else (recipe.resultQuantity * multiplier) - amount
            if (leftOver > 0 && !isLeftOver) addLeftOver(item, leftOver)
            for ((requiredItem, requiredAmount) in recipe.requirements) {
                processItem(requiredItem, requiredAmount * multiplier, isLeftOver, action)
            }
        }
    }

    //first, decomposition of given list of items
    for ((item, amount) in itemsToDecompose) {
        processItem(item, amount, false) { pItem, pAmount -> addRawMaterial(pItem, pAmount) }
    }

    //second, decomposition of leftovers
    for ((item, amount) in leftOvers) {
        if (!item.craftable) {
            addRawMaterialLeftOver(item, amount)
        } else {
            val recipe = recipeRegistry.getRecipesByResult(item)[0]
            if (amount >= recipe.resultQuantity) {
                val leftOver = amount % recipe.resultQuantity
                val requiredAmount = amount - leftOver
                addRawMaterialLeftOver(item,leftOver)
                processItem(item, requiredAmount, true) { pItem, pAmount ->
                    addRawMaterialLeftOver(
                        pItem,
                        pAmount
                    )
                }
            } else {
                addRawMaterialLeftOver(item, amount)
            }
        }
    }

    //remove raw materials from main raw material list and from leftover raw materials, leaving true leftover items
    for ((item, amount) in rawMaterialsLeftOvers){
        if (!item.craftable && rawMaterials.containsKey(item)){
            rawMaterials[item] = rawMaterials[item]!! - amount
            rawMaterialsLeftOvers.remove(item)
        }
    }

    return rawMaterials to rawMaterialsLeftOvers
}

/**
 * Function that will convert a map with item id to amount to item to amount.
 * @param itemsId a map containing item ids mapped to amounts.
 * @param itemRegistry an item registry to pull item data.
 * @return a map of item mapped to amounts.
 */
fun convertIdMapToItemMap(
    itemsId: Map<String, Int>,
    itemRegistry: ItemRegistry
): Map<Item, Int> {
    val itemToAmountMap = mutableMapOf<Item, Int>()

    for ((id, amount) in itemsId) {
        val item = itemRegistry.getItem(id)!!
        itemToAmountMap[item] = amount
    }

    return itemToAmountMap
}
