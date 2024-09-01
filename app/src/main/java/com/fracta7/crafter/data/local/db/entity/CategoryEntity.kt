package com.fracta7.crafter.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val key: Int? = 0,
    val id: String?,
    val name: String?,
    val item: String?,
    val custom: Boolean?
)