package com.fracta7.crafter.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_types")
data class RecipeTypeEntity(
    @PrimaryKey(autoGenerate = true) val key: Int? = null,
    val id: String?,
    val name: String?,
    val item: String?,
    val custom: Boolean?
)