package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable

typealias TagID = String
typealias TagName = String

/**
 * Minecraft Item.
 * @property id string id of an item.
 * @property name name of an item.
 * @property stackSize stack size of an item.
 * @property decomposable indicates if it is decomposable by decomposition function.
 * @property tags contains tags for categories.
 * @property custom indicates if item was custom added
 */
@Immutable
data class Item(
    val id: ItemID,
    val name: String,
    val stackSize: Int,
    val decomposable: Boolean,
    val tags: List<TagID> = listOf("other"),
    val custom: Boolean = false
)
