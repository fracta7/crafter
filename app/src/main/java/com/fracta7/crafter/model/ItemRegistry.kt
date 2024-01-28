package com.fracta7.crafter.model

class ItemRegistry {
    private val items: MutableMap<String, Item> = mutableMapOf()

    fun addItem(item: Item) {
        items[item.id] = item
    }

    fun getItemById(id: String): Item? {
        return items[id]
    }

    fun addAll(vararg items: Item){
        items.forEach { addItem(it) }
    }
}