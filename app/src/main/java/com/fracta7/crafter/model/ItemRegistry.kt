package com.fracta7.crafter.model

import android.content.Context
import com.fracta7.crafter.R

/**
 * Registry class to hold all items
 * @property addItem adds a single item to the registry.
 * @property getItem gets an item from registry with id.
 * @property addAll adds several items to the registry.
 */
class ItemRegistry {
    private val items: MutableMap<String, Item> = mutableMapOf()

    /**
     * Adds single item to the registry.
     * @param item Item to add.
     */
    fun addItem(item: Item) {
        items[item.id] = item
    }

    /**
     * Gets an item fom registry.
     * @param id id of an item.
     * @return nullable Item.
     */
    fun getItem(id: String): Item? {
        return items[id]
    }

    /**
     * Adds several items to the registry.
     * @param items vararg of items.
     */
    fun addAll(vararg items: Item){
        items.forEach { addItem(it) }
    }

    fun getAll(): Map<String, Item>{
        return items
    }
}