package com.fracta7.crafter.ui.main_activity

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.ui.elements.AddItemDialog
import com.fracta7.crafter.ui.elements.DrawItem
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.elements.ItemInfoDialog
import com.fracta7.crafter.ui.elements.ProjectNameDialog
import com.fracta7.crafter.ui.navigation.Route

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainActivityViewModel>()
    val catalogState by viewModel.catalogState.collectAsState()

    var showDrawer by remember { mutableStateOf(false) }
    var showSaveProjectDialog by remember { mutableStateOf(false) }
    var addItemId by remember { mutableStateOf<ItemID?>(null) }
    var editingItem by remember { mutableStateOf<Item?>(null) }
    var infoItemId by remember { mutableStateOf<ItemID?>(null) }
    val haptics = LocalHapticFeedback.current

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Crafter")
                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            Text(
                                text = catalogState.mcVersion,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { showDrawer = true }) {
                            BadgedBox(
                                badge = {
                                    if (viewModel.items.isNotEmpty()) {
                                        Badge { Text(text = viewModel.items.size.toString()) }
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.List,
                                    contentDescription = "Crafting queue"
                                )
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                AnimatedVisibility(visible = viewModel.items.isNotEmpty()) {
                    ExtendedFloatingActionButton(
                        onClick = {
                            viewModel.openDraftForCrafting { projectId ->
                                navController.navigate(Route.Crafting(projectId = projectId))
                            }
                        },
                        icon = { Icon(Icons.Rounded.Done, contentDescription = null) },
                        text = { Text("Craft (${viewModel.items.size})") }
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                OutlinedTextField(
                    value = catalogState.searchQuery,
                    onValueChange = viewModel::onSearchQueryChange,
                    shape = ShapeDefaults.ExtraLarge,
                    singleLine = true,
                    label = { Text(text = "Item name") },
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Rounded.Search, contentDescription = "Search icon")
                    },
                    trailingIcon = {
                        if (catalogState.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                Icon(
                                    Icons.Rounded.Clear,
                                    contentDescription = "Clear search query"
                                )
                            }
                        }
                    }
                )

                val allSelected = catalogState.selectedCategories.size == catalogState.categories.size
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item(key = "toggle_all_categories") {
                        FilterChip(
                            label = { Text(if (allSelected) "Clear" else "All") },
                            selected = allSelected,
                            onClick = viewModel::toggleAllCategories,
                            leadingIcon = if (allSelected) {
                                { Icon(Icons.Rounded.Check, contentDescription = null) }
                            } else {
                                null
                            },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    items(
                        items = catalogState.categories,
                        key = { "${it.id}_${it.name}" }
                    ) { category ->
                        val isSelected = category.id in catalogState.selectedCategories
                        FilterChip(
                            label = { Text(category.name) },
                            selected = isSelected,
                            onClick = { viewModel.toggleCategory(category.id) },
                            leadingIcon = if (isSelected) {
                                { Icon(Icons.Rounded.Check, contentDescription = null) }
                            } else {
                                null
                            },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 86.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        end = 8.dp,
                        top = 4.dp,
                        bottom = 88.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    catalogState.sections.forEach { section ->
                        item(
                            key = "header_${section.category.id}",
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, bottom = 2.dp, start = 4.dp)
                            ) {
                                DrawItem(
                                    itemID = section.category.item,
                                    modifier = Modifier.padding(end = 8.dp),
                                    iconSize = 28.dp
                                )
                                Text(
                                    text = section.category.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        items(
                            items = section.items,
                            key = { "${section.category.id}_${it.id}" }
                        ) { item ->
                            val queuedCount = viewModel.items[item]
                            CatalogGridItemCard(
                                item = item,
                                queuedCount = queuedCount,
                                onClick = { addItemId = item.id },
                                onLongClick = {
                                    haptics.performHapticFeedback(
                                        HapticFeedbackType.LongPress
                                    )
                                    infoItemId = item.id
                                }
                            )
                        }
                    }
                }
            }

            if (showDrawer) {
                ModalBottomSheet(
                    onDismissRequest = { showDrawer = false },
                    contentWindowInsets = { BottomSheetDefaults.windowInsets }
                ) {
                    val queueEntries = viewModel.items.entries.toList()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 120.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        if (queueEntries.isNotEmpty()) {
                            items(items = queueEntries, key = { it.key.id }) { (item, amount) ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .combinedClickable(onClick = { editingItem = item })
                                ) {
                                    ItemElement(
                                        modifier = Modifier.weight(1f),
                                        item = item,
                                        amount = amount,
                                        preview = false
                                    )
                                    IconButton(onClick = {
                                        viewModel.adjustItemAmount(item, -1)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.rounded_remove_24),
                                            contentDescription = "Decrease quantity"
                                        )
                                    }
                                    IconButton(onClick = {
                                        viewModel.adjustItemAmount(item, 1)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = "Increase quantity"
                                        )
                                    }
                                    IconButton(onClick = {
                                        viewModel.removeItem(item)
                                    }) {
                                        Icon(
                                            Icons.Rounded.Delete,
                                            contentDescription = "Remove the item"
                                        )
                                    }
                                }
                                HorizontalDivider()
                            }
                            item(key = "queue_actions") {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = { showSaveProjectDialog = true },
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("Save as Project")
                                    }
                                    FilledTonalButton(
                                        onClick = { viewModel.clearItems() },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.filledTonalButtonColors(
                                            containerColor = MaterialTheme.colorScheme.errorContainer
                                        )
                                    ) {
                                        Text("Clear all")
                                    }
                                }
                            }
                        } else {
                            item(key = "empty_queue") {
                                Text(
                                    text = "There are no items in the list.",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (showSaveProjectDialog) {
                ProjectNameDialog(
                    title = "Save as Project",
                    confirmLabel = "Save",
                    onDismissRequest = { showSaveProjectDialog = false },
                    onConfirm = { name ->
                        showSaveProjectDialog = false
                        showDrawer = false
                        viewModel.saveQueueAsProject(name) { projectId ->
                            navController.navigate(Route.Crafting(projectId = projectId))
                        }
                    }
                )
            }

            val pendingAddItem = addItemId?.let(viewModel::getItemById)
            if (pendingAddItem != null) {
                AddItemDialog(
                    onDismissRequest = { addItemId = null },
                    onConfirmation = { amount ->
                        viewModel.addItemToList(item = pendingAddItem, amount = amount)
                        addItemId = null
                    },
                    item = pendingAddItem,
                    existingAmountInQueue = viewModel.items[pendingAddItem] ?: 0
                )
            }

            val pendingEditItem = editingItem
            if (pendingEditItem != null) {
                AddItemDialog(
                    onDismissRequest = { editingItem = null },
                    onConfirmation = { amount ->
                        viewModel.setItemAmount(item = pendingEditItem, amount = amount)
                        editingItem = null
                    },
                    item = pendingEditItem,
                    initialAmount = viewModel.items[pendingEditItem] ?: 1,
                    confirmLabel = "Update"
                )
            }

            val recipePreferences by viewModel.recipePreferences.collectAsState()
            val infoItem = infoItemId?.let(viewModel::getItemById)
            if (infoItem != null) {
                key(recipePreferences, catalogState.mcVersion) {
                    ItemInfoDialog(
                        item = infoItem,
                        resolveItem = viewModel::getItemById,
                        getRecipesForResult = viewModel::getRecipesForResult,
                        getRecipesUsingIngredient = viewModel::getRecipesUsingIngredient,
                        resolveRecipeType = viewModel::getRecipeType,
                        resolveIngredientRef = viewModel::resolveIngredientRef,
                        preferredFuelItemId = recipePreferences.preferredFuelItemId ?: "coal",
                        onDismissRequest = { infoItemId = null }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CatalogGridItemCard(
    item: Item,
    queuedCount: Int?,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val isQueued = queuedCount != null && queuedCount > 0
    val colorScheme = MaterialTheme.colorScheme
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isQueued) {
            colorScheme.secondaryContainer.copy(alpha = 0.65f)
        } else {
            colorScheme.surfaceContainerLow
        },
        border = BorderStroke(
            width = if (isQueued) 1.6.dp else 1.dp,
            color = if (isQueued) colorScheme.primary else colorScheme.outlineVariant.copy(alpha = 0.55f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(98.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp, vertical = 6.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                DrawItem(
                    itemID = item.id,
                    iconSize = 46.dp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 11.sp,
                    lineHeight = 12.sp,
                    fontWeight = if (isQueued) FontWeight.Bold else FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AnimatedVisibility(
                visible = isQueued,
                enter = scaleIn(),
                exit = scaleOut(),
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Badge {
                    Text(text = (queuedCount ?: 0).toString())
                }
            }
        }
    }
}
