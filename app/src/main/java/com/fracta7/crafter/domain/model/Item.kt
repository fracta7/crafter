package com.fracta7.crafter.domain.model

/**
 * Minecraft Item
 * @property id string id of an item
 * @property name name of an item
 * @property stackSize stack size of an item
 * @property raw indicates if it is craftable
 */
data class Item(
    val id: String,
    val name: String,
    val stackSize: Int,
    val raw: Boolean
)
