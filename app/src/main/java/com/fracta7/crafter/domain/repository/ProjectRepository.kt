package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.ItemID
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun observeSavedProjects(): Flow<List<CraftingProject>>
    fun observeDraft(): Flow<CraftingProject?>
    suspend fun getDraft(): CraftingProject?
    suspend fun getProjectById(id: Long): CraftingProject?
    suspend fun saveDraftTargets(targets: Map<ItemID, Int>): Long
    suspend fun saveProject(
        name: String,
        targets: Map<ItemID, Int>,
        ownedInventory: Map<ItemID, Int> = emptyMap(),
        selectedRecipes: Map<ItemID, Int> = emptyMap()
    ): Long

    suspend fun updateProjectProgress(
        projectId: Long,
        ownedInventory: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>
    )

    suspend fun renameProject(projectId: Long, newName: String)
    suspend fun duplicateProject(projectId: Long): Long?
    suspend fun deleteProject(projectId: Long)
}
