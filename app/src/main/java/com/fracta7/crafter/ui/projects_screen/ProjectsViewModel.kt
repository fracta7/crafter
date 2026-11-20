package com.fracta7.crafter.ui.projects_screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.model.toRecipePreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.ProjectRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import com.fracta7.crafter.domain.usecases.ExportFormat
import com.fracta7.crafter.domain.usecases.LitematicaParseResult
import com.fracta7.crafter.domain.usecases.computeCraftingProjection
import com.fracta7.crafter.domain.usecases.formatMaterialList
import com.fracta7.crafter.domain.usecases.parseLitematicaMaterialList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class ProjectSummaryUi(
    val project: CraftingProject,
    val totalTargetItems: Int,
    val progressFraction: Float,
    val completedMaterials: Int,
    val totalMaterials: Int,
    val previewItemIds: List<ItemID>
)

@Immutable
data class ProjectsUiState(
    val draftSummary: ProjectSummaryUi? = null,
    val savedProjects: List<ProjectSummaryUi> = emptyList(),
    val showShulkerUnits: Boolean = true
)

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val projectRepository: ProjectRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private var latestPreferences = UserPreferences()

    val uiState: StateFlow<ProjectsUiState> = combine(
        projectRepository.observeSavedProjects(),
        projectRepository.observeDraft(),
        userPreferencesRepository.preferences,
        appRepository.registryState
    ) { savedProjects, draft, prefs, snapshot ->
        latestPreferences = prefs
        val draftSummary = draft
            ?.takeIf { it.targets.isNotEmpty() }
            ?.let { buildSummary(it, prefs, snapshot) }
        val savedSummaries = savedProjects.map { buildSummary(it, prefs, snapshot) }
        ProjectsUiState(
            draftSummary = draftSummary,
            savedProjects = savedSummaries,
            showShulkerUnits = prefs.showShulkerUnits
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProjectsUiState()
    )

    fun getItem(itemId: ItemID): Item? =
        appRepository.itemRegistryProvider().getItem(itemId)

    fun saveDraftAsProject(name: String, onSaved: (Long) -> Unit = {}) {
        val draft = uiState.value.draftSummary?.project ?: return
        if (draft.targets.isEmpty()) return
        viewModelScope.launch {
            val id = projectRepository.saveProject(
                name = name,
                targets = draft.targets,
                ownedInventory = draft.ownedInventory,
                selectedRecipes = draft.selectedRecipes
            )
            onSaved(id)
        }
    }

    fun renameProject(projectId: Long, newName: String) {
        viewModelScope.launch {
            projectRepository.renameProject(projectId, newName)
        }
    }

    fun duplicateProject(projectId: Long) {
        viewModelScope.launch {
            projectRepository.duplicateProject(projectId)
        }
    }

    fun deleteProject(projectId: Long) {
        viewModelScope.launch {
            projectRepository.deleteProject(projectId)
        }
    }

    fun parseLitematica(content: String): LitematicaParseResult {
        return parseLitematicaMaterialList(
            content = content,
            itemRegistry = appRepository.itemRegistryProvider()
        )
    }

    fun importLitematicaProject(
        name: String,
        targets: Map<ItemID, Int>,
        onImported: (Long) -> Unit = {}
    ) {
        if (targets.isEmpty()) return
        viewModelScope.launch {
            val id = projectRepository.saveProject(
                name = name,
                targets = targets
            )
            onImported(id)
        }
    }

    fun formatProjectExport(project: CraftingProject, format: ExportFormat): String {
        val snapshot = appRepository.registryState.value
        val projection = computeCraftingProjection(
            targets = project.targets,
            recipeRegistry = snapshot.recipeRegistry,
            itemRegistry = snapshot.itemRegistry,
            selectedRecipes = project.selectedRecipes,
            ownedInventory = project.ownedInventory,
            recipePreferences = latestPreferences.toRecipePreferences(),
            tagRegistry = snapshot.tagRegistry
        )
        return formatMaterialList(
            projectName = project.name,
            targets = project.targets,
            materialRows = projection.materialRows,
            leftovers = projection.plan.leftovers,
            resolveItem = ::getItem,
            format = format,
            showShulkerUnits = latestPreferences.showShulkerUnits
        )
    }

    private fun buildSummary(
        project: CraftingProject,
        prefs: UserPreferences,
        snapshot: RegistrySnapshot
    ): ProjectSummaryUi {
        val projection = computeCraftingProjection(
            targets = project.targets,
            recipeRegistry = snapshot.recipeRegistry,
            itemRegistry = snapshot.itemRegistry,
            selectedRecipes = project.selectedRecipes,
            ownedInventory = project.ownedInventory,
            recipePreferences = prefs.toRecipePreferences(),
            tagRegistry = snapshot.tagRegistry
        )
        return ProjectSummaryUi(
            project = project,
            totalTargetItems = project.targets.values.sum(),
            progressFraction = projection.progress.fraction,
            completedMaterials = projection.progress.completedMaterials,
            totalMaterials = projection.progress.totalMaterials,
            previewItemIds = project.targets.keys.take(5)
        )
    }
}
