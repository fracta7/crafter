package com.fracta7.crafter

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.RegistryManifest
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.model.TagRegistry
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.ProjectRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class FakeProjectRepository(
    initialProjects: List<CraftingProject> = emptyList()
) : ProjectRepository {

    private var nextId = (initialProjects.maxOfOrNull { it.id } ?: 0L) + 1L
    private val store = MutableStateFlow(initialProjects)

    override fun observeSavedProjects(): Flow<List<CraftingProject>> =
        store.map { list -> list.filter { !it.isDraft }.sortedByDescending { it.updatedAt } }

    override fun observeDraft(): Flow<CraftingProject?> =
        store.map { list -> list.firstOrNull { it.isDraft } }

    override suspend fun getDraft(): CraftingProject? =
        store.value.firstOrNull { it.isDraft }

    override suspend fun getProjectById(id: Long): CraftingProject? =
        store.value.firstOrNull { it.id == id }

    override suspend fun saveDraftTargets(targets: Map<ItemID, Int>): Long {
        val positive = targets.filterValues { it > 0 }
        val existing = store.value.firstOrNull { it.isDraft }
        val now = System.currentTimeMillis()
        return if (existing != null) {
            val updated = existing.copy(
                targets = positive,
                ownedInventory = if (positive.isEmpty()) emptyMap() else existing.ownedInventory,
                selectedRecipes = if (positive.isEmpty()) emptyMap() else existing.selectedRecipes,
                updatedAt = now
            )
            store.update { list -> list.map { if (it.id == updated.id) updated else it } }
            updated.id
        } else {
            val created = CraftingProject(
                id = nextId++,
                name = "Current Draft",
                isDraft = true,
                targets = positive,
                createdAt = now,
                updatedAt = now
            )
            store.update { it + created }
            created.id
        }
    }

    override suspend fun saveProject(
        name: String,
        targets: Map<ItemID, Int>,
        ownedInventory: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>
    ): Long {
        val now = System.currentTimeMillis()
        val created = CraftingProject(
            id = nextId++,
            name = name.trim().ifBlank { "Untitled Project" },
            isDraft = false,
            targets = targets.filterValues { it > 0 },
            ownedInventory = ownedInventory.filterValues { it > 0 },
            selectedRecipes = selectedRecipes,
            createdAt = now,
            updatedAt = now
        )
        store.update { it + created }
        return created.id
    }

    override suspend fun updateProjectProgress(
        projectId: Long,
        ownedInventory: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>
    ) {
        val now = System.currentTimeMillis()
        store.update { list ->
            list.map { project ->
                if (project.id == projectId) {
                    project.copy(
                        ownedInventory = ownedInventory.filterValues { it > 0 },
                        selectedRecipes = selectedRecipes,
                        updatedAt = now
                    )
                } else {
                    project
                }
            }
        }
    }

    override suspend fun renameProject(projectId: Long, newName: String) {
        val clean = newName.trim()
        if (clean.isBlank()) return
        store.update { list ->
            list.map { if (it.id == projectId) it.copy(name = clean) else it }
        }
    }

    override suspend fun duplicateProject(projectId: Long): Long? {
        val existing = getProjectById(projectId) ?: return null
        val id = nextId++
        store.update { it + existing.copy(id = id, name = "${existing.name} (Copy)", isDraft = false) }
        return id
    }

    override suspend fun deleteProject(projectId: Long) {
        store.update { list -> list.filterNot { it.id == projectId } }
    }
}

class FakeUserPreferencesRepository(
    initial: UserPreferences = UserPreferences()
) : UserPreferencesRepository {
    private val _preferences = MutableStateFlow(initial)
    override val preferences: Flow<UserPreferences> = _preferences.asStateFlow()

    override suspend fun setPreferredFuel(fuel: PreferredFuel) {
        _preferences.update { it.copy(preferredFuel = fuel) }
    }

    override suspend fun setPreferredWood(wood: PreferredWood) {
        _preferences.update { it.copy(preferredWood = wood) }
    }

    override suspend fun setPreferredStone(stone: PreferredStone) {
        _preferences.update { it.copy(preferredStone = stone) }
    }

    override suspend fun setPreferStonecutter(prefer: Boolean) {
        _preferences.update { it.copy(preferStonecutter = prefer) }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        _preferences.update { it.copy(themeMode = mode) }
    }

    override suspend fun setDynamicColor(enabled: Boolean) {
        _preferences.update { it.copy(dynamicColor = enabled) }
    }

    override suspend fun setUseMojanglesFont(enabled: Boolean) {
        _preferences.update { it.copy(useMojanglesFont = enabled) }
    }

    override suspend fun setShowShulkerUnits(enabled: Boolean) {
        _preferences.update { it.copy(showShulkerUnits = enabled) }
    }

    override suspend fun setKeepScreenOnWhileCrafting(enabled: Boolean) {
        _preferences.update { it.copy(keepScreenOnWhileCrafting = enabled) }
    }

    override suspend fun setLastRegistryCheckMillis(timestamp: Long) {
        _preferences.update { it.copy(lastRegistryCheckMillis = timestamp) }
    }
}

class FakeAppRepository(
    initialSnapshot: RegistrySnapshot
) : AppRepository {
    private val _registryState = MutableStateFlow(initialSnapshot)
    override val registryState: kotlinx.coroutines.flow.StateFlow<RegistrySnapshot> =
        _registryState.asStateFlow()

    override fun itemRegistryProvider(): ItemRegistry = _registryState.value.itemRegistry

    override fun recipeRegistryProvider(): RecipeRegistry = _registryState.value.recipeRegistry

    override fun tagRegistryProvider(): TagRegistry = _registryState.value.tagRegistry

    override fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType =
        _registryState.value.recipeTypes.getValue(recipeTypeID)

    override fun getAllTags(): List<Category> = _registryState.value.categories

    override fun updateSnapshot(snapshot: RegistrySnapshot) {
        _registryState.value = snapshot
    }

    companion object {
        fun from(
            items: ItemRegistry,
            recipes: RecipeRegistry,
            recipeTypes: Map<RecipeTypeID, RecipeType>,
            categories: List<Category> = emptyList(),
            tags: TagRegistry = TagRegistry(),
            manifest: RegistryManifest = RegistryManifest()
        ): FakeAppRepository {
            return FakeAppRepository(
                RegistrySnapshot(
                    manifest = manifest,
                    source = RegistrySource.BUNDLED,
                    itemRegistry = items,
                    recipeRegistry = recipes,
                    tagRegistry = tags,
                    categories = categories,
                    recipeTypes = recipeTypes
                )
            )
        }
    }
}
