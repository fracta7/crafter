package com.fracta7.crafter.ui.crafting_screen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.usecases.CraftingProgress
import com.fracta7.crafter.ui.crafting_screen.components.MaterialsTab
import com.fracta7.crafter.ui.crafting_screen.components.StepsTab
import com.fracta7.crafter.ui.crafting_screen.components.TreeTab
import com.fracta7.crafter.ui.crafting_screen.components.TreeViewMode
import com.fracta7.crafter.ui.elements.ProjectNameDialog
import com.fracta7.crafter.ui.projects_screen.ExportMaterialListDialog
import kotlinx.coroutines.launch

private enum class CraftingTab(val title: String) {
    MATERIALS("Materials"),
    STEPS("Steps"),
    TREE("Tree")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftingScreen(navController: NavController, projectId: Long) {
    val viewModel = hiltViewModel<CraftingScreenViewModel>()
    LaunchedEffect(projectId) {
        viewModel.loadProject(projectId)
    }

    val uiState by viewModel.uiState.collectAsState()
    val tabs = CraftingTab.entries
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    var showSaveDialog by remember { mutableStateOf(false) }
    var showExportDialog by remember { mutableStateOf(false) }
    var treeViewMode by remember { mutableStateOf(TreeViewMode.DIAGRAM) }

    val view = LocalView.current
    DisposableEffect(uiState.keepScreenOn) {
        view.keepScreenOn = uiState.keepScreenOn
        onDispose {
            view.keepScreenOn = false
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = uiState.projectName.ifBlank { "Crafter" },
                            maxLines = 1,
                            modifier = Modifier.basicMarquee()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        if (uiState.isDraft && uiState.targets.isNotEmpty()) {
                            TextButton(onClick = { showSaveDialog = true }) {
                                Text("Save")
                            }
                        }
                        if (uiState.targets.isNotEmpty()) {
                            IconButton(onClick = { showExportDialog = true }) {
                                Icon(
                                    imageVector = Icons.Rounded.Share,
                                    contentDescription = "Export material list"
                                )
                            }
                        }
                        if (uiState.hasOwnedItems) {
                            IconButton(onClick = viewModel::resetOwnedInventory) {
                                Icon(
                                    imageVector = Icons.Rounded.Refresh,
                                    contentDescription = "Reset progress"
                                )
                            }
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ProgressHeader(
                    progress = uiState.progress,
                    leftoverCount = uiState.plan.leftovers.size
                )

                PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                    tabs.forEachIndexed { index, tab ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(index) }
                            },
                            text = { Text(text = tab.title) }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    beyondViewportPageCount = 2,
                    userScrollEnabled = !(tabs[pagerState.currentPage] == CraftingTab.TREE &&
                        treeViewMode == TreeViewMode.DIAGRAM),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    when (tabs[page]) {
                        CraftingTab.MATERIALS -> MaterialsTab(
                            materialRows = uiState.materialRows,
                            leftovers = uiState.plan.leftovers,
                            resolveItem = viewModel::getItem,
                            onToggleComplete = viewModel::toggleItemComplete,
                            onSetOwnedAmount = viewModel::setOwnedAmount
                        )

                        CraftingTab.STEPS -> StepsTab(
                            stepRows = uiState.stepRows,
                            resolveItem = viewModel::getItem,
                            resolveRecipeType = viewModel::getRecipeType,
                            getRecipesForResult = viewModel::getRecipesForResult,
                            resolveIngredientRef = viewModel::resolveIngredientRef,
                            preferredFuelItemId = uiState.recipePreferences.preferredFuelItemId ?: "coal",
                            onSelectRecipe = viewModel::selectRecipe,
                            onToggleComplete = viewModel::toggleItemComplete,
                            onSetOwnedAmount = viewModel::setOwnedAmount
                        )

                        CraftingTab.TREE -> TreeTab(
                            targets = uiState.targets,
                            materialRows = uiState.materialRows,
                            stepRows = uiState.stepRows,
                            ownedInventory = uiState.ownedInventory,
                            treeNodes = uiState.plan.tree,
                            viewMode = treeViewMode,
                            onViewModeChange = { treeViewMode = it },
                            resolveItem = viewModel::getItem,
                            resolveRecipeType = viewModel::getRecipeType,
                            getRecipesForResult = viewModel::getRecipesForResult,
                            onSelectRecipe = viewModel::selectRecipe,
                            onSetOwnedAmount = viewModel::setOwnedAmount
                        )
                    }
                }
            }
        }
    }

    if (showSaveDialog) {
        ProjectNameDialog(
            title = "Save as Project",
            confirmLabel = "Save",
            onDismissRequest = { showSaveDialog = false },
            onConfirm = { name ->
                showSaveDialog = false
                viewModel.saveDraftAsProject(name)
            }
        )
    }

    if (showExportDialog) {
        ExportMaterialListDialog(
            projectName = uiState.projectName,
            formatContent = viewModel::formatExport,
            onDismissRequest = { showExportDialog = false }
        )
    }
}

@Composable
private fun ProgressHeader(
    progress: CraftingProgress,
    leftoverCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val percent = (progress.fraction * 100).toInt()
            Text(
                text = "$percent% ready",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            val leftoverSuffix = if (leftoverCount > 0) " • $leftoverCount leftovers" else ""
            Text(
                text = "${progress.completedMaterials}/${progress.totalMaterials} materials • ${progress.completedSteps}/${progress.totalSteps} steps$leftoverSuffix",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = { progress.fraction },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
