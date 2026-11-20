package com.fracta7.crafter.ui.main_activity

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.TagID
import com.fracta7.crafter.domain.model.toRecipePreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.ProjectRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import com.fracta7.crafter.domain.usecases.RecipePreferences
import com.fracta7.crafter.domain.usecases.resolveIngredientRef as resolveRefUseCase
import com.fracta7.crafter.domain.usecases.resolvePreferredRecipes
import com.fracta7.crafter.domain.usecases.resolveRecipesUsingIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_QUEUE_ITEM_AMOUNT = 999_999
private const val DRAFT_SAVE_DEBOUNCE_MS = 150L

@Immutable
data class CatalogSection(
    val category: Category,
    val items: List<Item>
)

@Immutable
data class CatalogUiState(
    val searchQuery: String = "",
    val categories: List<Category> = emptyList(),
    val selectedCategories: Set<TagID> = emptySet(),
    val sections: List<CatalogSection> = emptyList(),
    val mcVersion: String = "26.3"
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val projectRepository: ProjectRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private var activeSnapshot: RegistrySnapshot = appRepository.registryState.value
    private var categories: List<Category> = activeSnapshot.categories
    private var itemsByCategory: Map<TagID, List<Item>> = indexItemsByCategory(activeSnapshot)

    val items = mutableStateMapOf<Item, Int>()
    private var draftSaveJob: Job? = null

    private val _recipePreferences = MutableStateFlow(RecipePreferences())
    val recipePreferences: StateFlow<RecipePreferences> = _recipePreferences.asStateFlow()

    private val _catalogState = MutableStateFlow(
        buildCatalogUiState(
            searchQuery = "",
            selectedCategories = categories.map { it.id }.toSet(),
            mcVersion = activeSnapshot.manifest.mcVersion
        )
    )
    val catalogState: StateFlow<CatalogUiState> = _catalogState.asStateFlow()

    init {
        viewModelScope.launch {
            appRepository.registryState.collect { snapshot ->
                val hadAllSelected = _catalogState.value.selectedCategories.size == categories.size &&
                    categories.isNotEmpty()
                activeSnapshot = snapshot
                categories = snapshot.categories
                itemsByCategory = indexItemsByCategory(snapshot)
                val nextSelected = if (hadAllSelected) {
                    categories.map { it.id }.toSet()
                } else {
                    val validIds = categories.map { it.id }.toSet()
                    _catalogState.value.selectedCategories.intersect(validIds)
                }
                _catalogState.value = buildCatalogUiState(
                    searchQuery = _catalogState.value.searchQuery,
                    selectedCategories = nextSelected,
                    mcVersion = snapshot.manifest.mcVersion
                )
            }
        }
        viewModelScope.launch {
            userPreferencesRepository.preferences.collect { prefs ->
                _recipePreferences.value = prefs.toRecipePreferences()
            }
        }
        viewModelScope.launch {
            val draft = projectRepository.getDraft()
            if (draft != null && items.isEmpty() && draft.targets.isNotEmpty()) {
                val registry = appRepository.itemRegistryProvider()
                draft.targets.forEach { (id, qty) ->
                    val item = registry.getItem(id)
                    if (item != null && qty > 0) {
                        items[item] = qty.coerceAtMost(MAX_QUEUE_ITEM_AMOUNT)
                    }
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _catalogState.update { current ->
            if (current.searchQuery == query) return@update current
            buildCatalogUiState(query, current.selectedCategories, current.mcVersion)
        }
    }

    fun toggleCategory(tagId: TagID) {
        _catalogState.update { current ->
            val updated = if (tagId in current.selectedCategories) {
                current.selectedCategories - tagId
            } else {
                current.selectedCategories + tagId
            }
            buildCatalogUiState(current.searchQuery, updated, current.mcVersion)
        }
    }

    fun toggleAllCategories() {
        _catalogState.update { current ->
            val allIds = categories.map { it.id }.toSet()
            val updated = if (current.selectedCategories.size == allIds.size) {
                emptySet()
            } else {
                allIds
            }
            buildCatalogUiState(current.searchQuery, updated, current.mcVersion)
        }
    }

    fun selectOnlyCategory(tagId: TagID) {
        _catalogState.update { current ->
            buildCatalogUiState(current.searchQuery, setOf(tagId), current.mcVersion)
        }
    }

    fun addItemToList(item: Item, amount: Int) {
        if (amount <= 0) return
        val next = ((items[item] ?: 0) + amount).coerceAtMost(MAX_QUEUE_ITEM_AMOUNT)
        items[item] = next
        scheduleDraftSave()
    }

    fun setItemAmount(item: Item, amount: Int) {
        if (amount <= 0) {
            items.remove(item)
        } else {
            items[item] = amount.coerceAtMost(MAX_QUEUE_ITEM_AMOUNT)
        }
        scheduleDraftSave()
    }

    fun adjustItemAmount(item: Item, delta: Int) {
        val current = items[item] ?: 0
        setItemAmount(item, current + delta)
    }

    fun removeItem(item: Item) {
        items.remove(item)
        scheduleDraftSave()
    }

    fun clearItems() {
        items.clear()
        scheduleDraftSave()
    }

    fun openDraftForCrafting(onReady: (Long) -> Unit) {
        val snapshot = currentTargetsSnapshot()
        if (snapshot.isEmpty()) return
        draftSaveJob?.cancel()
        viewModelScope.launch {
            val draftId = projectRepository.saveDraftTargets(snapshot)
            onReady(draftId)
        }
    }

    fun saveQueueAsProject(name: String, onSaved: (Long) -> Unit) {
        val snapshot = currentTargetsSnapshot()
        if (snapshot.isEmpty()) return
        viewModelScope.launch {
            val projectId = projectRepository.saveProject(name = name, targets = snapshot)
            onSaved(projectId)
        }
    }

    fun getItemById(itemID: ItemID): Item? =
        appRepository.itemRegistryProvider().getItem(itemID)

    fun getRecipesForResult(itemID: ItemID): List<Recipe> =
        resolvePreferredRecipes(
            recipes = appRepository.recipeRegistryProvider().getRecipesByResult(itemID),
            preferences = _recipePreferences.value,
            itemRegistry = appRepository.itemRegistryProvider(),
            tagRegistry = appRepository.tagRegistryProvider()
        )

    fun getRecipesUsingIngredient(itemID: ItemID): List<Recipe> =
        resolveRecipesUsingIngredient(
            itemId = itemID,
            recipeRegistry = appRepository.recipeRegistryProvider(),
            preferences = _recipePreferences.value,
            itemRegistry = appRepository.itemRegistryProvider(),
            tagRegistry = appRepository.tagRegistryProvider()
        )

    fun resolveIngredientRef(ref: IngredientRef): ItemID =
        resolveRefUseCase(
            ref = ref,
            tagRegistry = appRepository.tagRegistryProvider(),
            preferences = _recipePreferences.value,
            itemRegistry = appRepository.itemRegistryProvider()
        )

    fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType =
        appRepository.getRecipeType(recipeTypeID)

    private fun currentTargetsSnapshot(): Map<ItemID, Int> =
        items.entries.associate { (item, qty) -> item.id to qty }

    private fun scheduleDraftSave() {
        val snapshot = currentTargetsSnapshot()
        draftSaveJob?.cancel()
        draftSaveJob = viewModelScope.launch {
            delay(DRAFT_SAVE_DEBOUNCE_MS)
            projectRepository.saveDraftTargets(snapshot)
        }
    }

    private fun indexItemsByCategory(snapshot: RegistrySnapshot): Map<TagID, List<Item>> =
        buildMap {
            val allItems = snapshot.itemRegistry.getAll().values
            snapshot.categories.forEach { category ->
                put(category.id, allItems.filter { category.id in it.tags })
            }
        }

    private fun buildCatalogUiState(
        searchQuery: String,
        selectedCategories: Set<TagID>,
        mcVersion: String
    ): CatalogUiState {
        val trimmedQuery = searchQuery.trim()
        val sections = categories.mapNotNull { category ->
            if (category.id !in selectedCategories) return@mapNotNull null
            val categoryItems = itemsByCategory[category.id].orEmpty()
            val matching = if (trimmedQuery.isEmpty()) {
                categoryItems
            } else {
                categoryItems.filter { it.name.contains(trimmedQuery, ignoreCase = true) }
            }
            if (matching.isEmpty()) null else CatalogSection(category = category, items = matching)
        }
        return CatalogUiState(
            searchQuery = searchQuery,
            categories = categories,
            selectedCategories = selectedCategories,
            sections = sections,
            mcVersion = mcVersion
        )
    }
}
