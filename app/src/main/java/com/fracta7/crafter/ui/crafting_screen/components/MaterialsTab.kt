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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.usecases.MaterialRowUi
import com.fracta7.crafter.domain.usecases.getStackText
import com.fracta7.crafter.ui.elements.DrawItem
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.theme.LocalShowShulkerUnits

@Composable
fun MaterialsTab(
    materialRows: List<MaterialRowUi>,
    leftovers: Map<ItemID, Int>,
    resolveItem: (ItemID) -> Item?,
    onToggleComplete: (ItemID, Int) -> Unit,
    onSetOwnedAmount: (ItemID, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var editingRow by remember { mutableStateOf<MaterialRowUi?>(null) }
    var collectedExpanded by remember { mutableStateOf(false) }

    val pendingRows = remember(materialRows) { materialRows.filter { !it.isCompleted } }
    val completedRows = remember(materialRows) { materialRows.filter { it.isCompleted } }
    val leftoverEntries = remember(leftovers) { leftovers.entries.toList() }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (materialRows.isEmpty() && leftoverEntries.isEmpty()) {
            item(key = "empty_materials") {
                Text(
                    text = "No raw materials required.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (pendingRows.isEmpty() && completedRows.isNotEmpty()) {
            item(key = "all_gathered_banner") {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "All raw materials gathered!",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        items(items = pendingRows, key = { "pending_${it.itemId}" }) { row ->
            val item = resolveItem(row.itemId) ?: return@items
            MaterialRowItem(
                item = item,
                row = row,
                onToggle = { onToggleComplete(row.itemId, row.totalRequired) },
                onEditOwned = { editingRow = row }
            )
            HorizontalDivider()
        }

        if (completedRows.isNotEmpty()) {
            item(key = "collected_header") {
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { collectedExpanded = !collectedExpanded }
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
                                text = "Collected (${completedRows.size})",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = if (collectedExpanded) {
                                    Icons.Rounded.KeyboardArrowUp
                                } else {
                                    Icons.Rounded.KeyboardArrowDown
                                },
                                contentDescription = null
                            )
                        }
                        AnimatedVisibility(visible = collectedExpanded) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                completedRows.forEach { row ->
                                    val item = resolveItem(row.itemId)
                                    if (item != null) {
                                        HorizontalDivider()
                                        MaterialRowItem(
                                            item = item,
                                            row = row,
                                            onToggle = {
                                                onToggleComplete(row.itemId, row.totalRequired)
                                            },
                                            onEditOwned = { editingRow = row }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (leftoverEntries.isNotEmpty()) {
            item(key = "leftovers_header") {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Leftovers (${leftoverEntries.size})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
            items(items = leftoverEntries, key = { "leftover_${it.key}" }) { (itemId, amount) ->
                val item = resolveItem(itemId) ?: return@items
                ItemElement(
                    modifier = Modifier.fillMaxWidth(),
                    item = item,
                    amount = amount,
                    preview = false,
                    iconSize = 48.dp
                )
                HorizontalDivider()
            }
        }
    }

    val activeEdit = editingRow
    val editItem = activeEdit?.let { resolveItem(it.itemId) }
    if (activeEdit != null && editItem != null) {
        OwnedQuantityDialog(
            item = editItem,
            requiredAmount = activeEdit.totalRequired,
            currentOwned = activeEdit.owned,
            onDismissRequest = { editingRow = null },
            onConfirm = { newOwned ->
                onSetOwnedAmount(activeEdit.itemId, newOwned)
            }
        )
    }
}

@Composable
private fun MaterialRowItem(
    item: Item,
    row: MaterialRowUi,
    onToggle: () -> Unit,
    onEditOwned: () -> Unit
) {
    val contentAlpha = if (row.isCompleted) 0.6f else 1f
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEditOwned)
            .padding(vertical = 4.dp)
            .alpha(contentAlpha)
    ) {
        Checkbox(
            checked = row.isCompleted,
            onCheckedChange = { onToggle() }
        )
        DrawItem(itemID = item.id, iconSize = 48.dp)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textDecoration = if (row.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 1,
                modifier = Modifier.basicMarquee()
            )
            val displayAmount = if (row.isCompleted) row.totalRequired else row.remaining
            Text(
                text = getStackText(
                    amount = displayAmount,
                    stackSize = item.stackSize,
                    showShulkerUnits = LocalShowShulkerUnits.current
                ),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                modifier = Modifier.basicMarquee()
            )
        }
        if (row.owned > 0 && !row.isCompleted) {
            SuggestionChip(
                onClick = onEditOwned,
                label = {
                    Text(
                        text = "Have ${row.owned}/${row.totalRequired}",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}
