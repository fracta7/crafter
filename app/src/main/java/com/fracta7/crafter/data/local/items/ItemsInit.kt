package com.fracta7.crafter.data.local.items

import com.fracta7.crafter.domain.model.Item

/**
 * Function to initiate all items.
 * @return List of Items
 */
fun itemsInit(): List<Item> {
    return getBuildingItems() +
            getColoredItems() +
            getNaturalItems() +
            getFunctionalItems() +
            getRedstoneItems() +
            getToolItems() +
            getCombatItems() +
            getConsumableItems() +
            getIngredientItems() +
            getPotionItems()
}