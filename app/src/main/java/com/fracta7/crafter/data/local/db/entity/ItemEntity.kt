package com.fracta7.crafter.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val key: Int? = null,
    val id: String?,
    val name: String?,
    val stackSize: Int?,
    val craftable: Boolean?,
    val tags: String?, // Will store the JSON representation of the list
    val custom: Boolean?
)