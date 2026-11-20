package com.fracta7.crafter.data.repository

import com.fracta7.crafter.data.local.db.dao.ProjectDao
import com.fracta7.crafter.data.local.db.entity.ProjectEntity
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override fun observeSavedProjects(): Flow<List<CraftingProject>> =
        projectDao.observeSavedProjects().map { list -> list.map { it.toDomain() } }

    override fun observeDraft(): Flow<CraftingProject?> =
        projectDao.observeDraft().map { it?.toDomain() }

    override suspend fun getDraft(): CraftingProject? =
        projectDao.getDraft()?.toDomain()

    override suspend fun getProjectById(id: Long): CraftingProject? =
        projectDao.getById(id)?.toDomain()

    override suspend fun saveDraftTargets(targets: Map<ItemID, Int>): Long {
        val now = System.currentTimeMillis()
        val positiveTargets = targets.filterValues { it > 0 }
        val existing = projectDao.getDraft()
        return if (existing != null) {
            val keepProgress = positiveTargets.isNotEmpty()
            val updated = existing.copy(
                targetsJson = encodeIntMap(positiveTargets),
                ownedInventoryJson = if (keepProgress) existing.ownedInventoryJson else "{}",
                selectedRecipesJson = if (keepProgress) existing.selectedRecipesJson else "{}",
                updatedAt = now
            )
            projectDao.update(updated)
            updated.id
        } else {
            projectDao.upsert(
                ProjectEntity(
                    name = "Current Draft",
                    isDraft = true,
                    targetsJson = encodeIntMap(positiveTargets),
                    ownedInventoryJson = "{}",
                    selectedRecipesJson = "{}",
                    createdAt = now,
                    updatedAt = now
                )
            )
        }
    }

    override suspend fun saveProject(
        name: String,
        targets: Map<ItemID, Int>,
        ownedInventory: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>
    ): Long {
        val now = System.currentTimeMillis()
        val cleanName = name.trim().ifBlank { "Untitled Project" }
        return projectDao.upsert(
            ProjectEntity(
                name = cleanName,
                isDraft = false,
                targetsJson = encodeIntMap(targets.filterValues { it > 0 }),
                ownedInventoryJson = encodeIntMap(ownedInventory.filterValues { it > 0 }),
                selectedRecipesJson = encodeIntMap(selectedRecipes),
                createdAt = now,
                updatedAt = now
            )
        )
    }

    override suspend fun updateProjectProgress(
        projectId: Long,
        ownedInventory: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>
    ) {
        val existing = projectDao.getById(projectId) ?: return
        projectDao.update(
            existing.copy(
                ownedInventoryJson = encodeIntMap(ownedInventory.filterValues { it > 0 }),
                selectedRecipesJson = encodeIntMap(selectedRecipes),
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun renameProject(projectId: Long, newName: String) {
        val cleanName = newName.trim()
        if (cleanName.isBlank()) return
        val existing = projectDao.getById(projectId) ?: return
        projectDao.update(
            existing.copy(
                name = cleanName,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun duplicateProject(projectId: Long): Long? {
        val existing = projectDao.getById(projectId) ?: return null
        val now = System.currentTimeMillis()
        return projectDao.upsert(
            existing.copy(
                id = 0,
                name = "${existing.name} (Copy)",
                isDraft = false,
                createdAt = now,
                updatedAt = now
            )
        )
    }

    override suspend fun deleteProject(projectId: Long) {
        projectDao.deleteById(projectId)
    }

    private fun encodeIntMap(map: Map<String, Int>): String =
        json.encodeToString(map)

    private fun decodeIntMap(raw: String): Map<String, Int> {
        if (raw.isBlank()) return emptyMap()
        return runCatching { json.decodeFromString<Map<String, Int>>(raw) }
            .getOrDefault(emptyMap())
    }

    private fun ProjectEntity.toDomain(): CraftingProject = CraftingProject(
        id = id,
        name = name,
        isDraft = isDraft,
        targets = decodeIntMap(targetsJson),
        ownedInventory = decodeIntMap(ownedInventoryJson),
        selectedRecipes = decodeIntMap(selectedRecipesJson),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
