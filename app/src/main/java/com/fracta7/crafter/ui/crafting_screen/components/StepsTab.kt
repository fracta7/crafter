package com.fracta7.crafter.ui.crafting_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.IngredientRef
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.usecases.StepRowUi
import com.fracta7.crafter.ui.elements.DrawItem
import com.fracta7.crafter.ui.elements.RecipeDialog
import com.fracta7.crafter.ui.elements.RecipeGridView

private enum class StepGrouping(val label: String) {
    ORDER("By Order"),
    STATION("By Station")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepsTab(
    stepRows: List<StepRowUi>,
    resolveItem: (ItemID) -> Item?,
    resolveRecipeType: (RecipeTypeID) -> RecipeType,
    getRecipesForResult: (ItemID) -> List<Recipe>,
    resolveIngredientRef: (IngredientRef) -> ItemID = { ref ->
        when (ref) {
            is IngredientRef.Item -> ref.id
            is IngredientRef.Tag -> ref.id.removePrefix("minecraft:")
        }
    },
    preferredFuelItemId: ItemID = "coal",
    onSelectRecipe: (ItemID, Int) -> Unit,
    onToggleComplete: (ItemID, Int) -> Unit,
    onSetOwnedAmount: (ItemID, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var grouping by remember { mutableStateOf(StepGrouping.ORDER) }
    var completedExpanded by remember { mutableStateOf(false) }
    var editingStep by remember { mutableStateOf<StepRowUi?>(null) }
    var recipeSwapStep by remember { mutableStateOf<StepRowUi?>(null) }
    var expandedPatternIds by remember { mutableStateOf(emptySet<ItemID>()) }

    val activeSteps = remember(stepRows) { stepRows.filter { !it.isCompleted } }
    val completedSteps = remember(stepRows) { stepRows.filter { it.isCompleted } }
    val groupedByStation = remember(activeSteps) {
        activeSteps.groupBy { resolveRecipeType(it.recipe.recipeType) }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (stepRows.isEmpty()) {
            item(key = "no_steps") {
                Text(
                    text = "No crafting steps needed — all targets are raw materials.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
            return@LazyColumn
        }

        item(key = "grouping_toggle") {
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                StepGrouping.entries.forEachIndexed { index, option ->
                    SegmentedButton(
                        selected = grouping == option,
                        onClick = { grouping = option },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = StepGrouping.entries.size
                        ),
                        label = { Text(option.label) }
                    )
                }
            }
        }

        if (activeSteps.isEmpty()) {
            item(key = "all_steps_done") {
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "All crafting steps completed!",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else if (grouping == StepGrouping.ORDER) {
            items(items = activeSteps, key = { "step_${it.itemId}" }) { step ->
                StepCard(
                    step = step,
                    resolveItem = resolveItem,
                    recipeType = resolveRecipeType(step.recipe.recipeType),
                    resolveRecipeType = resolveRecipeType,
                    resolveIngredientRef = resolveIngredientRef,
                    preferredFuelItemId = preferredFuelItemId,
                    isPatternExpanded = step.itemId in expandedPatternIds,
                    onTogglePattern = {
                        expandedPatternIds = if (step.itemId in expandedPatternIds) {
                            expandedPatternIds - step.itemId
                        } else {
                            expandedPatternIds + step.itemId
                        }
                    },
                    onToggleComplete = { onToggleComplete(step.itemId, step.totalRequired) },
                    onEditOwned = { editingStep = step },
                    onSwapRecipe = { recipeSwapStep = step }
                )
            }
        } else {
            groupedByStation.forEach { (recipeType, stepsForStation) ->
                item(key = "station_header_${recipeType.id}") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 2.dp)
                    ) {
                        DrawItem(itemID = recipeType.item, iconSize = 28.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${recipeType.name} (${stepsForStation.size})",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                items(
                    items = stepsForStation,
                    key = { "station_${recipeType.id}_${it.itemId}" }
                ) { step ->
                    StepCard(
                        step = step,
                        resolveItem = resolveItem,
                        recipeType = recipeType,
                        resolveRecipeType = resolveRecipeType,
                        resolveIngredientRef = resolveIngredientRef,
                        preferredFuelItemId = preferredFuelItemId,
                        isPatternExpanded = step.itemId in expandedPatternIds,
                        onTogglePattern = {
                            expandedPatternIds = if (step.itemId in expandedPatternIds) {
                                expandedPatternIds - step.itemId
                            } else {
                                expandedPatternIds + step.itemId
                            }
                        },
                        onToggleComplete = { onToggleComplete(step.itemId, step.totalRequired) },
                        onEditOwned = { editingStep = step },
                        onSwapRecipe = { recipeSwapStep = step }
                    )
                }
            }
        }

        if (completedSteps.isNotEmpty()) {
            item(key = "completed_steps_section") {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { completedExpanded = !completedExpanded }
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "Completed Steps (${completedSteps.size})",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = if (completedExpanded) {
                                    Icons.Rounded.KeyboardArrowUp
                                } else {
                                    Icons.Rounded.KeyboardArrowDown
                                },
                                contentDescription = null
                            )
                        }
                        AnimatedVisibility(visible = completedExpanded) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                completedSteps.forEach { step ->
                                    val outputItem = resolveItem(step.itemId)
                                    if (outputItem != null) {
                                        HorizontalDivider()
                                        CompletedStepRow(
                                            step = step,
                                            item = outputItem,
                                            onToggleComplete = {
                                                onToggleComplete(step.itemId, step.totalRequired)
                                            },
                                            onEditOwned = { editingStep = step }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    val activeEdit = editingStep
    val editItem = activeEdit?.let { resolveItem(it.itemId) }
    if (activeEdit != null && editItem != null) {
        OwnedQuantityDialog(
            item = editItem,
            requiredAmount = activeEdit.totalRequired,
            currentOwned = activeEdit.owned,
            onDismissRequest = { editingStep = null },
            onConfirm = { newOwned ->
                onSetOwnedAmount(activeEdit.itemId, newOwned)
            }
        )
    }

    val activeSwap = recipeSwapStep
    if (activeSwap != null) {
        val recipes = getRecipesForResult(activeSwap.itemId)
        if (recipes.size > 1) {
            RecipeDialog(
                recipes = recipes,
                onDismissRequest = { recipeSwapStep = null },
                onItemSelected = { index ->
                    onSelectRecipe(activeSwap.itemId, index)
                },
                selected = activeSwap.selectedRecipeIndex,
                resolveItem = resolveItem,
                resolveRecipeType = resolveRecipeType
            )
        }
    }
}

@Composable
private fun StepCard(
    step: StepRowUi,
    resolveItem: (ItemID) -> Item?,
    recipeType: RecipeType,
    resolveRecipeType: (RecipeTypeID) -> RecipeType,
    resolveIngredientRef: (IngredientRef) -> ItemID,
    preferredFuelItemId: ItemID,
    isPatternExpanded: Boolean,
    onTogglePattern: () -> Unit,
    onToggleComplete: () -> Unit,
    onEditOwned: () -> Unit,
    onSwapRecipe: () -> Unit
) {
    val outputItem = resolveItem(step.itemId) ?: return
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEditOwned)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { onToggleComplete() }
                )
                DrawItem(itemID = outputItem.id, iconSize = 44.dp)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "${outputItem.name} ×${step.totalRequired - step.owned}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DrawItem(itemID = recipeType.item, iconSize = 18.dp)
                        Spacer(modifier = Modifier.width(4.dp))
                        val leftoverNote = if (step.leftover > 0) " • ${step.leftover} leftover" else ""
                        Text(
                            text = "${recipeType.name} • ${step.craftCount}× batch$leftoverNote",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            modifier = Modifier.basicMarquee()
                        )
                    }
                }
                if (step.availableRecipeCount > 1) {
                    IconButton(onClick = onSwapRecipe) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_swap_horiz_24),
                            contentDescription = "Switch recipe"
                        )
                    }
                }
                if (step.recipe.layout != null) {
                    IconButton(onClick = onTogglePattern) {
                        Icon(
                            imageVector = if (isPatternExpanded) {
                                Icons.Rounded.KeyboardArrowUp
                            } else {
                                Icons.Rounded.KeyboardArrowDown
                            },
                            contentDescription = "Toggle recipe grid"
                        )
                    }
                }
            }

            if (step.owned > 0) {
                SuggestionChip(
                    onClick = onEditOwned,
                    label = {
                        Text(
                            text = "Have ${step.owned} / ${step.totalRequired}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(6.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 12.dp)
            ) {
                step.recipe.requirements.forEach { (reqId, reqQty) ->
                    val reqItem = resolveItem(reqId)
                    val totalInput = reqQty * step.craftCount
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DrawItem(itemID = reqId, iconSize = 24.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${reqItem?.name ?: reqId} ×$totalInput",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            modifier = Modifier.basicMarquee()
                        )
                    }
                }
            }

            AnimatedVisibility(visible = isPatternExpanded && step.recipe.layout != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 8.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
                    RecipeGridView(
                        recipe = step.recipe,
                        resolveRecipeType = resolveRecipeType,
                        resolveIngredientRef = resolveIngredientRef,
                        preferredFuelItemId = preferredFuelItemId
                    )
                }
            }
        }
    }
}

@Composable
private fun CompletedStepRow(
    step: StepRowUi,
    item: Item,
    onToggleComplete: () -> Unit,
    onEditOwned: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !step.isSatisfiedByParent, onClick = onEditOwned)
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .alpha(0.65f)
    ) {
        Checkbox(
            checked = true,
            enabled = !step.isSatisfiedByParent,
            onCheckedChange = { onToggleComplete() }
        )
        DrawItem(itemID = item.id, iconSize = 36.dp)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "${item.name} ×${step.totalRequired}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.LineThrough,
                maxLines = 1,
                modifier = Modifier.basicMarquee()
            )
            if (step.isSatisfiedByParent) {
                Text(
                    text = "Covered by owned parent item",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
