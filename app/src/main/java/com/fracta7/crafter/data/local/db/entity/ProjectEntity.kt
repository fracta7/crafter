package com.fracta7.crafter.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val isDraft: Boolean = false,
    val targetsJson: String,
    val ownedInventoryJson: String,
    val selectedRecipesJson: String,
    val createdAt: Long,
    val updatedAt: Long
)
