package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class CraftingProject(
    val id: Long = 0L,
    val name: String,
    val isDraft: Boolean = false,
    val targets: Map<ItemID, Int> = emptyMap(),
    val ownedInventory: Map<ItemID, Int> = emptyMap(),
    val selectedRecipes: Map<ItemID, Int> = emptyMap(),
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)
