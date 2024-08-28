package com.fracta7.crafter.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val result: String?,
    val resultQuantity: Int?,
    val requirements: String?, // Will store the JSON representation of the map
    val recipeType: String?,
    val custom: Boolean?
)