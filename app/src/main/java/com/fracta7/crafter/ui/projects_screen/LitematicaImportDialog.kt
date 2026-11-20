package com.fracta7.crafter.ui.projects_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.usecases.LitematicaParseResult
import com.fracta7.crafter.ui.elements.DrawItem

private enum class LitematicaQuantityMode(val label: String) {
    MISSING("Missing"),
    TOTAL("Total")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LitematicaImportDialog(
    parseResult: LitematicaParseResult,
    defaultName: String,
    resolveItem: (ItemID) -> Item?,
    onDismissRequest: () -> Unit,
    onConfirmImport: (projectName: String, targets: Map<ItemID, Int>) -> Unit
) {
    var projectName by remember(parseResult) {
        mutableStateOf(parseResult.suggestedProjectName ?: defaultName)
    }
    var mode by remember(parseResult) { mutableStateOf(LitematicaQuantityMode.MISSING) }
    var unmatchedExpanded by remember(parseResult) { mutableStateOf(false) }

    val selectedTargets = remember(parseResult, mode) {
        buildMap {
            parseResult.matched.forEach { entry ->
                val itemId = entry.matchedItemId ?: return@forEach
                val qty = if (mode == LitematicaQuantityMode.MISSING) entry.missing else entry.total
                if (qty > 0) {
                    put(itemId, (get(itemId) ?: 0) + qty)
                }
            }
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Import Litematica List",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = projectName,
                    onValueChange = { projectName = it },
                    label = { Text("Project name") },
                    singleLine = true,
                    shape = ShapeDefaults.ExtraLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    LitematicaQuantityMode.entries.forEachIndexed { index, option ->
                        SegmentedButton(
                            selected = mode == option,
                            onClick = { mode = option },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = LitematicaQuantityMode.entries.size
                            ),
                            label = { Text(option.label) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${selectedTargets.size} matched item types (${selectedTargets.values.sum()} total items)",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                if (parseResult.unmatched.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { unmatchedExpanded = !unmatchedExpanded }
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "${parseResult.unmatched.size} unmatched entries",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.error
                                )
                                Icon(
                                    imageVector = if (unmatchedExpanded) {
                                        Icons.Rounded.KeyboardArrowUp
                                    } else {
                                        Icons.Rounded.KeyboardArrowDown
                                    },
                                    contentDescription = null
                                )
                            }
                            AnimatedVisibility(visible = unmatchedExpanded) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    parseResult.unmatched.forEach { entry ->
                                        Text(
                                            text = "• ${entry.rawName} (×${entry.total})",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 220.dp)
                ) {
                    if (selectedTargets.isEmpty()) {
                        item(key = "empty_import") {
                            Text(
                                text = "No positive item quantities found for '${mode.label}'.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }
                    } else {
                        items(
                            items = selectedTargets.entries.toList(),
                            key = { it.key }
                        ) { (itemId, qty) ->
                            val item = resolveItem(itemId)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                DrawItem(itemID = itemId, iconSize = 28.dp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = item?.name ?: itemId,
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .weight(1f)
                                        .basicMarquee()
                                )
                                Text(
                                    text = "×$qty",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    TextButton(
                        enabled = projectName.trim().isNotEmpty() && selectedTargets.isNotEmpty(),
                        onClick = { onConfirmImport(projectName.trim(), selectedTargets) }
                    ) {
                        Text("Import")
                    }
                }
            }
        }
    }
}
