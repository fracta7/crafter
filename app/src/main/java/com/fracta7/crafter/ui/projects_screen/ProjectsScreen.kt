package com.fracta7.crafter.ui.projects_screen

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.model.CraftingProject
import com.fracta7.crafter.domain.usecases.LitematicaParseResult
import com.fracta7.crafter.ui.elements.DrawItem
import com.fracta7.crafter.ui.elements.ProjectNameDialog
import com.fracta7.crafter.ui.navigation.Route
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

private data class PendingImport(
    val result: LitematicaParseResult,
    val fallbackName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(navController: NavController) {
    val viewModel = hiltViewModel<ProjectsViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var showSaveDraftDialog by remember { mutableStateOf(false) }
    var renamingProject by remember { mutableStateOf<CraftingProject?>(null) }
    var exportingProject by remember { mutableStateOf<CraftingProject?>(null) }
    var pendingImport by remember { mutableStateOf<PendingImport?>(null) }

    val openDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        val text = readTextFromUri(context, uri)
        if (text == null) {
            scope.launch { snackbarHostState.showSnackbar("Unable to read selected file.") }
            return@rememberLauncherForActivityResult
        }
        val parsed = viewModel.parseLitematica(text)
        if (parsed.matched.isEmpty() && parsed.unmatched.isEmpty()) {
            scope.launch {
                snackbarHostState.showSnackbar("No Litematica items recognized in file.")
            }
            return@rememberLauncherForActivityResult
        }
        val fallbackName = readDisplayName(context, uri)
            ?.substringBeforeLast('.')
            ?.takeIf { it.isNotBlank() }
            ?: "Litematica Import"
        pendingImport = PendingImport(result = parsed, fallbackName = fallbackName)
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Projects") })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    openDocumentLauncher.launch(
                        arrayOf("text/plain", "text/csv", "text/comma-separated-values", "*/*")
                    )
                },
                icon = { Icon(Icons.Rounded.Add, contentDescription = null) },
                text = { Text("Import Litematica") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val draftSummary = uiState.draftSummary
            if (draftSummary != null) {
                item(key = "active_draft_card") {
                    ActiveDraftCard(
                        summary = draftSummary,
                        onOpen = {
                            navController.navigate(Route.Crafting(draftSummary.project.id))
                        },
                        onSaveAsProject = { showSaveDraftDialog = true }
                    )
                }
            }

            if (uiState.savedProjects.isEmpty()) {
                item(key = "empty_projects_state") {
                    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "No saved projects yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Queue items in Catalog and tap \"Save as Project\", or import a Litematica .txt / .csv material list.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                item(key = "saved_header") {
                    Text(
                        text = "Saved Projects (${uiState.savedProjects.size})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                items(
                    items = uiState.savedProjects,
                    key = { it.project.id }
                ) { summary ->
                    SavedProjectCard(
                        summary = summary,
                        onOpen = { navController.navigate(Route.Crafting(summary.project.id)) },
                        onRename = { renamingProject = summary.project },
                        onDuplicate = { viewModel.duplicateProject(summary.project.id) },
                        onExport = { exportingProject = summary.project },
                        onDelete = { viewModel.deleteProject(summary.project.id) }
                    )
                }
            }
        }
    }

    if (showSaveDraftDialog) {
        ProjectNameDialog(
            title = "Save Draft as Project",
            confirmLabel = "Save",
            onDismissRequest = { showSaveDraftDialog = false },
            onConfirm = { name ->
                showSaveDraftDialog = false
                viewModel.saveDraftAsProject(name)
            }
        )
    }

    val targetRename = renamingProject
    if (targetRename != null) {
        ProjectNameDialog(
            title = "Rename Project",
            initialName = targetRename.name,
            confirmLabel = "Rename",
            onDismissRequest = { renamingProject = null },
            onConfirm = { newName ->
                renamingProject = null
                viewModel.renameProject(targetRename.id, newName)
            }
        )
    }

    val targetExport = exportingProject
    if (targetExport != null) {
        ExportMaterialListDialog(
            projectName = targetExport.name,
            formatContent = { format -> viewModel.formatProjectExport(targetExport, format) },
            onDismissRequest = { exportingProject = null }
        )
    }

    val currentImport = pendingImport
    if (currentImport != null) {
        LitematicaImportDialog(
            parseResult = currentImport.result,
            defaultName = currentImport.fallbackName,
            resolveItem = viewModel::getItem,
            onDismissRequest = { pendingImport = null },
            onConfirmImport = { name, targets ->
                pendingImport = null
                viewModel.importLitematicaProject(name, targets) { newId ->
                    navController.navigate(Route.Crafting(newId))
                }
            }
        )
    }
}

@Composable
private fun ActiveDraftCard(
    summary: ProjectSummaryUi,
    onOpen: () -> Unit,
    onSaveAsProject: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onOpen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "ACTIVE CATALOG QUEUE",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${summary.project.targets.size} item types (${summary.totalTargetItems} total)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            PreviewIconStrip(
                itemIds = summary.previewItemIds,
                extraCount = (summary.project.targets.size - summary.previewItemIds.size).coerceAtLeast(0)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onSaveAsProject,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save as Project")
                }
                Button(
                    onClick = onOpen,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Open")
                }
            }
        }
    }
}

@Composable
private fun SavedProjectCard(
    summary: ProjectSummaryUi,
    onOpen: () -> Unit,
    onRename: () -> Unit,
    onDuplicate: () -> Unit,
    onExport: () -> Unit,
    onDelete: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val dateLabel = remember(summary.project.updatedAt) {
        DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
            .format(Date(summary.project.updatedAt))
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onOpen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = summary.project.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                    Text(
                        text = "${summary.project.targets.size} targets (${summary.totalTargetItems} items) • $dateLabel",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                }
                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Rounded.MoreVert, contentDescription = "Project options")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Rename") },
                            onClick = {
                                menuExpanded = false
                                onRename()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Duplicate") },
                            onClick = {
                                menuExpanded = false
                                onDuplicate()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Export / Share") },
                            onClick = {
                                menuExpanded = false
                                onExport()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                menuExpanded = false
                                onDelete()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            PreviewIconStrip(
                itemIds = summary.previewItemIds,
                extraCount = (summary.project.targets.size - summary.previewItemIds.size).coerceAtLeast(0)
            )
            Spacer(modifier = Modifier.height(10.dp))

            val percent = (summary.progressFraction * 100).toInt()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$percent% complete",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${summary.completedMaterials}/${summary.totalMaterials} raw materials",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { summary.progressFraction },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun PreviewIconStrip(
    itemIds: List<String>,
    extraCount: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemIds.forEach { itemId ->
            DrawItem(itemID = itemId, iconSize = 28.dp)
        }
        if (extraCount > 0) {
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "+$extraCount",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun readTextFromUri(context: Context, uri: Uri): String? {
    return runCatching {
        context.contentResolver.openInputStream(uri)?.bufferedReader()?.use { it.readText() }
    }.getOrNull()
}

private fun readDisplayName(context: Context, uri: Uri): String? {
    return runCatching {
        context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (idx >= 0) cursor.getString(idx) else null
                } else {
                    null
                }
            }
    }.getOrNull()
}
