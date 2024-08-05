package com.fracta7.crafter.util

import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

/**
 * Function that will decompose a list of items to raw items with left-overs.
 * @param itemsToDecompose a map of items to be decomposed.
 * @param recipeRegistry a recipe registry to pull recipes.
 * @return returns a pair of raw materials mapped to left-overs. First - raw material Map<Item, Int>, second - left-overs Map<Item, Int>.
 */
fun decomposeItems(
    itemsToDecompose: Map<ItemID, Int>,
    recipeRegistry: RecipeRegistry,
    itemRegistry: ItemRegistry
): Pair<Map<ItemID, Int>, Map<ItemID, Int>> {
    val rawMaterials = mutableMapOf<ItemID, Int>() //main raw material list
    val leftOvers = mutableMapOf<ItemID, Int>() //internal list of leftovers
    val rawMaterialsLeftOvers = mutableMapOf<ItemID, Int>() //processed list of leftovers
    val itemsToRemove = mutableMapOf<ItemID, Int>()

    fun addRawMaterial(item: ItemID, amount: Int) {
        rawMaterials[item] = rawMaterials.getOrDefault(item, 0) + amount
    }

    fun addRawMaterialLeftOver(item: ItemID, amount: Int) {
        rawMaterialsLeftOvers[item] = rawMaterialsLeftOvers.getOrDefault(item, 0) + amount
    }

    fun addLeftOver(item: ItemID, amount: Int) {
        leftOvers[item] = leftOvers.getOrDefault(item, 0) + amount
    }

    /**
     * Recursive function that will decompose a list of items
     * @param item item to be decomposed
     * @param amount amount of items to be decomposed
     * @param isLeftOver boolean to indicated if certain functionality is used for decomposing leftovers
     * @param action a function that will keep track of decomposed items
     */
    fun processItem(item: ItemID, amount: Int, isLeftOver: Boolean, action: (ItemID, Int) -> Unit) {
        val itemObject = itemRegistry.getItem(item)!!
        if (!itemObject.craftable) {
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
        val itemObject = itemRegistry.getItem(item)!!
        if (!itemObject.craftable) {
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
        val itemObject = itemRegistry.getItem(item)!!
        if (!itemObject.craftable && rawMaterials.containsKey(item)){
            rawMaterials[item] = rawMaterials[item]!! - amount
            //rawMaterialsLeftOvers.remove(item)
            itemsToRemove[item] = itemsToRemove.getOrDefault(item,0) + amount
        }
    }

    //remove leftovers that has been decomposed
    for ((item, _) in itemsToRemove){
        rawMaterialsLeftOvers.remove(item)
    }
    val zeroItems = mutableListOf<ItemID>()

    // Iterate over the items map
    rawMaterialsLeftOvers.forEach { (itemID, quantity) ->
        if (quantity == 0) {
            zeroItems.add(itemID)  // Add to list
            // Increment the count in updatedMap
            rawMaterials[itemID] = rawMaterials.getOrDefault(itemID, 0) + 1
        }
    }

    // Remove all entries with a value of 0
    rawMaterialsLeftOvers.entries.removeIf { it.value == 0 }
// https://stackoverflow.com/questions/50032000/how-to-avoid-concurrentmodificationexception-kotlin
    return rawMaterials to rawMaterialsLeftOvers
}
