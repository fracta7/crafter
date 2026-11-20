package com.fracta7.crafter.ui.crafting_screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.model.toRecipePreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.ProjectRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import com.fracta7.crafter.domain.usecases.CraftingPlan
import com.fracta7.crafter.domain.usecases.CraftingProgress
import com.fracta7.crafter.domain.usecases.ExportFormat
import com.fracta7.crafter.domain.usecases.MaterialRowUi
import com.fracta7.crafter.domain.usecases.RecipePreferences
import com.fracta7.crafter.domain.usecases.StepRowUi
import com.fracta7.crafter.domain.usecases.computeCraftingProjection
import com.fracta7.crafter.domain.usecases.formatMaterialList
import com.fracta7.crafter.domain.usecases.resolveIngredientRef as resolveRefUseCase
import com.fracta7.crafter.domain.usecases.resolvePreferredRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class CraftingUiState(
    val projectId: Long? = null,
    val projectName: String = "Crafter",
    val isDraft: Boolean = true,
    val targets: Map<ItemID, Int> = emptyMap(),
    val selectedRecipes: Map<ItemID, Int> = emptyMap(),
    val ownedInventory: Map<ItemID, Int> = emptyMap(),
    val plan: CraftingPlan = CraftingPlan(),
    val materialRows: List<MaterialRowUi> = emptyList(),
    val stepRows: List<StepRowUi> = emptyList(),
    val progress: CraftingProgress = CraftingProgress(),
    val recipePreferences: RecipePreferences = RecipePreferences(),
    val showShulkerUnits: Boolean = true,
    val keepScreenOn: Boolean = false
) {
    val hasOwnedItems: Boolean
        get() = ownedInventory.values.any { it > 0 }
}

@HiltViewModel
class CraftingScreenViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val projectRepository: ProjectRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CraftingUiState())
    val uiState: StateFlow<CraftingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userPreferencesRepository.preferences,
                appRepository.registryState
            ) { prefs, _ -> prefs }.collect { prefs ->
                applyPreferences(prefs)
            }
        }
    }

    fun loadProject(projectId: Long) {
        viewModelScope.launch {
            val project = projectRepository.getProjectById(projectId) ?: return@launch
            _uiState.update { current ->
                buildUiState(
                    projectId = project.id,
                    projectName = project.name,
                    isDraft = project.isDraft,
                    targets = project.targets,
                    selectedRecipes = project.selectedRecipes,
                    ownedInventory = project.ownedInventory,
                    recipePreferences = current.recipePreferences,
                    showShulkerUnits = current.showShulkerUnits,
                    keepScreenOn = current.keepScreenOn
                )
            }
        }
    }

    fun selectRecipe(itemId: ItemID, recipeIndex: Int) {
        var persistedState: CraftingUiState? = null
        _uiState.update { current ->
            val updatedSelections = current.selectedRecipes.toMutableMap().apply {
                this[itemId] = recipeIndex
            }
            if (updatedSelections == current.selectedRecipes) return@update current
            val next = buildUiState(
                projectId = current.projectId,
                projectName = current.projectName,
                isDraft = current.isDraft,
                targets = current.targets,
                selectedRecipes = updatedSelections,
                ownedInventory = current.ownedInventory,
                recipePreferences = current.recipePreferences,
                showShulkerUnits = current.showShulkerUnits,
                keepScreenOn = current.keepScreenOn
            )
            persistedState = next
            next
        }
        persistedState?.let(::persistProgress)
    }

    fun setOwnedAmount(itemId: ItemID, amount: Int) {
        val clamped = amount.coerceAtLeast(0)
        var persistedState: CraftingUiState? = null
        _uiState.update { current ->
            val updatedOwned = current.ownedInventory.toMutableMap().apply {
                if (clamped == 0) remove(itemId) else this[itemId] = clamped
            }
            if (updatedOwned == current.ownedInventory) return@update current
            val next = buildUiState(
                projectId = current.projectId,
                projectName = current.projectName,
                isDraft = current.isDraft,
                targets = current.targets,
                selectedRecipes = current.selectedRecipes,
                ownedInventory = updatedOwned,
                recipePreferences = current.recipePreferences,
                showShulkerUnits = current.showShulkerUnits,
                keepScreenOn = current.keepScreenOn
            )
            persistedState = next
            next
        }
        persistedState?.let(::persistProgress)
    }

    fun toggleItemComplete(itemId: ItemID, requiredTotal: Int) {
        if (requiredTotal <= 0) return
        var persistedState: CraftingUiState? = null
        _uiState.update { current ->
            val currentOwned = current.ownedInventory[itemId] ?: 0
            val nextOwned = if (currentOwned >= requiredTotal) 0 else requiredTotal
            val updatedOwned = current.ownedInventory.toMutableMap().apply {
                if (nextOwned == 0) remove(itemId) else this[itemId] = nextOwned
            }
            val next = buildUiState(
                projectId = current.projectId,
                projectName = current.projectName,
                isDraft = current.isDraft,
                targets = current.targets,
                selectedRecipes = current.selectedRecipes,
                ownedInventory = updatedOwned,
                recipePreferences = current.recipePreferences,
                showShulkerUnits = current.showShulkerUnits,
                keepScreenOn = current.keepScreenOn
            )
            persistedState = next
            next
        }
        persistedState?.let(::persistProgress)
    }

    fun resetOwnedInventory() {
        var persistedState: CraftingUiState? = null
        _uiState.update { current ->
            if (current.ownedInventory.isEmpty()) return@update current
            val next = buildUiState(
                projectId = current.projectId,
                projectName = current.projectName,
                isDraft = current.isDraft,
                targets = current.targets,
                selectedRecipes = current.selectedRecipes,
                ownedInventory = emptyMap(),
                recipePreferences = current.recipePreferences,
                showShulkerUnits = current.showShulkerUnits,
                keepScreenOn = current.keepScreenOn
            )
            persistedState = next
            next
        }
        persistedState?.let(::persistProgress)
    }

    fun saveDraftAsProject(name: String, onSaved: (Long) -> Unit = {}) {
        val snapshot = _uiState.value
        if (snapshot.targets.isEmpty()) return
        viewModelScope.launch {
            val savedId = projectRepository.saveProject(
                name = name,
                targets = snapshot.targets,
                ownedInventory = snapshot.ownedInventory,
                selectedRecipes = snapshot.selectedRecipes
            )
            _uiState.update { current ->
                current.copy(
                    projectId = savedId,
                    projectName = name.trim().ifBlank { "Untitled Project" },
                    isDraft = false
                )
            }
            onSaved(savedId)
        }
    }

    fun formatExport(format: ExportFormat): String {
        val current = _uiState.value
        return formatMaterialList(
            projectName = current.projectName,
            targets = current.targets,
            materialRows = current.materialRows,
            leftovers = current.plan.leftovers,
            resolveItem = ::getItem,
            format = format,
            showShulkerUnits = current.showShulkerUnits
        )
    }

    fun getItem(itemId: ItemID): Item? =
        appRepository.itemRegistryProvider().getItem(itemId)

    fun getRecipeType(recipeTypeId: RecipeTypeID): RecipeType =
        appRepository.getRecipeType(recipeTypeId)

    fun getRecipesForResult(itemId: ItemID): List<Recipe> =
        resolvePreferredRecipes(
            recipes = appRepository.recipeRegistryProvider().getRecipesByResult(itemId),
            preferences = _uiState.value.recipePreferences,
            itemRegistry = appRepository.itemRegistryProvider(),
            tagRegistry = appRepository.tagRegistryProvider()
        )

    fun resolveIngredientRef(ref: IngredientRef): ItemID =
        resolveRefUseCase(
            ref = ref,
            tagRegistry = appRepository.tagRegistryProvider(),
            preferences = _uiState.value.recipePreferences,
            itemRegistry = appRepository.itemRegistryProvider()
        )

    private fun applyPreferences(prefs: UserPreferences) {
        val recipePrefs = prefs.toRecipePreferences()
        _uiState.update { current ->
            buildUiState(
                projectId = current.projectId,
                projectName = current.projectName,
                isDraft = current.isDraft,
                targets = current.targets,
                selectedRecipes = current.selectedRecipes,
                ownedInventory = current.ownedInventory,
                recipePreferences = recipePrefs,
                showShulkerUnits = prefs.showShulkerUnits,
                keepScreenOn = prefs.keepScreenOnWhileCrafting
            )
        }
    }

    private fun persistProgress(state: CraftingUiState) {
        val id = state.projectId ?: return
        viewModelScope.launch {
            projectRepository.updateProjectProgress(
                projectId = id,
                ownedInventory = state.ownedInventory,
                selectedRecipes = state.selectedRecipes
            )
        }
    }

    private fun buildUiState(
        projectId: Long?,
        projectName: String,
        isDraft: Boolean,
        targets: Map<ItemID, Int>,
        selectedRecipes: Map<ItemID, Int>,
        ownedInventory: Map<ItemID, Int>,
        recipePreferences: RecipePreferences,
        showShulkerUnits: Boolean,
        keepScreenOn: Boolean
    ): CraftingUiState {
        val projection = computeCraftingProjection(
            targets = targets,
            recipeRegistry = appRepository.recipeRegistryProvider(),
            itemRegistry = appRepository.itemRegistryProvider(),
            selectedRecipes = selectedRecipes,
            ownedInventory = ownedInventory,
            recipePreferences = recipePreferences,
            tagRegistry = appRepository.tagRegistryProvider()
        )
        return CraftingUiState(
            projectId = projectId,
            projectName = projectName,
            isDraft = isDraft,
            targets = targets,
            selectedRecipes = selectedRecipes,
            ownedInventory = ownedInventory,
            plan = projection.plan,
            materialRows = projection.materialRows,
            stepRows = projection.stepRows,
            progress = projection.progress,
            recipePreferences = recipePreferences,
            showShulkerUnits = showShulkerUnits,
            keepScreenOn = keepScreenOn
        )
    }
}
