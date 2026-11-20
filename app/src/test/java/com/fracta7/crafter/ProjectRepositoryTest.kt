package com.fracta7.crafter

import com.fracta7.crafter.data.local.db.dao.ProjectDao
import com.fracta7.crafter.data.local.db.entity.ProjectEntity
import com.fracta7.crafter.data.repository.ProjectRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ProjectRepositoryTest {

    private class InMemoryProjectDao : ProjectDao {
        private var nextId = 1L
        private val rows = MutableStateFlow<List<ProjectEntity>>(emptyList())

        override fun observeSavedProjects(): Flow<List<ProjectEntity>> =
            rows.map { list -> list.filter { !it.isDraft }.sortedByDescending { it.updatedAt } }

        override fun observeDraft(): Flow<ProjectEntity?> =
            rows.map { list -> list.firstOrNull { it.isDraft } }

        override suspend fun getDraft(): ProjectEntity? =
            rows.value.firstOrNull { it.isDraft }

        override suspend fun getById(id: Long): ProjectEntity? =
            rows.value.firstOrNull { it.id == id }

        override suspend fun upsert(entity: ProjectEntity): Long {
            val assignedId = if (entity.id == 0L) nextId++ else entity.id
            val stored = entity.copy(id = assignedId)
            rows.update { list ->
                list.filterNot { it.id == assignedId } + stored
            }
            return assignedId
        }

        override suspend fun update(entity: ProjectEntity) {
            rows.update { list ->
                list.map { if (it.id == entity.id) entity else it }
            }
        }

        override suspend fun deleteById(id: Long) {
            rows.update { list -> list.filterNot { it.id == id } }
        }
    }

    @Test
    fun persistsDraftAndSavedProjectLifecycle() = runTest {
        val repo = ProjectRepositoryImpl(InMemoryProjectDao())

        val draftId = repo.saveDraftTargets(mapOf("piston" to 4))
        assertEquals(mapOf("piston" to 4), repo.getProjectById(draftId)?.targets)

        repo.updateProjectProgress(
            projectId = draftId,
            ownedInventory = mapOf("redstone" to 2),
            selectedRecipes = mapOf("fuel" to 1)
        )
        // Updating non-empty draft keeps owned progress; clearing targets resets it.
        repo.saveDraftTargets(mapOf("piston" to 8))
        assertEquals(mapOf("redstone" to 2), repo.getDraft()?.ownedInventory)
        repo.saveDraftTargets(emptyMap())
        assertTrue(repo.getDraft()?.ownedInventory?.isEmpty() == true)

        val projectId = repo.saveProject(
            name = "Beacon",
            targets = mapOf("beacon" to 1),
            ownedInventory = mapOf("obsidian" to 3),
            selectedRecipes = emptyMap()
        )
        assertEquals(1, repo.observeSavedProjects().first().size)

        repo.renameProject(projectId, "Main Beacon")
        assertEquals("Main Beacon", repo.getProjectById(projectId)?.name)

        val copyId = repo.duplicateProject(projectId)
        assertNotNull(copyId)
        assertEquals("Main Beacon (Copy)", repo.getProjectById(copyId!!)?.name)
        assertEquals(2, repo.observeSavedProjects().first().size)

        repo.deleteProject(projectId)
        assertNull(repo.getProjectById(projectId))
        assertEquals(1, repo.observeSavedProjects().first().size)
    }
}
