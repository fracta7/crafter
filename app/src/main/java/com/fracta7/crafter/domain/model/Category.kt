package com.fracta7.crafter.domain.model

/**
 * Represents item category
 * @property id is the ID of category
 * @property name is the name of category
 * @property item is the item ID of category
*/
data class Category(
    val id: TagID,
    val name: TagName,
    val item: ItemID,
    val custom: Boolean = false
)