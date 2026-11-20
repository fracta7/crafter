package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.usecases.SLOTS_PER_SHULKER

private const val MAX_ITEM_AMOUNT = 999_999

@Composable
fun AddItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (Int) -> Unit,
    item: Item,
    initialAmount: Int = 0,
    existingAmountInQueue: Int = 0,
    confirmLabel: String = "Add"
) {
    val startAmount = initialAmount.coerceIn(0, MAX_ITEM_AMOUNT)
    var input by remember(item.id, startAmount) {
        mutableStateOf(if (startAmount > 0) startAmount.toString() else "")
    }
    var itemAmount by remember(item.id, startAmount) { mutableIntStateOf(startAmount) }
    var isError by remember(item.id, startAmount) { mutableStateOf(false) }
    var errorText by remember(item.id, startAmount) { mutableStateOf("") }

    val quickChips = remember(item.stackSize) {
        when {
            item.stackSize >= 64 -> listOf(
                "+1" to 1,
                "+16" to 16,
                "+64" to 64,
                "+1 shulker" to (SLOTS_PER_SHULKER * item.stackSize)
            )
            item.stackSize == 16 -> listOf(
                "+1" to 1,
                "+4" to 4,
                "+16" to 16,
                "+1 shulker" to (SLOTS_PER_SHULKER * item.stackSize)
            )
            else -> listOf(
                "+1" to 1,
                "+9" to 9,
                "+27" to 27
            )
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        DrawItem(
                            modifier = Modifier.padding(4.dp),
                            itemID = item.id,
                            iconSize = 72.dp
                        )
                    }
                    item {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.basicMarquee(),
                            maxLines = 1
                        )
                        val subtitle = when {
                            startAmount > 0 -> "Update item quantity"
                            existingAmountInQueue > 0 -> "Currently in list: $existingAmountInQueue"
                            else -> "Add this item to the crafting list"
                        }
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = input,
                            onValueChange = { next ->
                                if (next.isBlank()) {
                                    input = next
                                    isError = true
                                    errorText = "(Amount cannot be empty)"
                                    itemAmount = 0
                                } else if (next.isDigitsOnly()) {
                                    input = next
                                    val parsed = next.toIntOrNull()
                                    if (parsed == null || parsed > MAX_ITEM_AMOUNT) {
                                        isError = true
                                        errorText = "(Max 999,999)"
                                        itemAmount = 0
                                    } else if (parsed == 0) {
                                        isError = true
                                        errorText = "(Must be > 0)"
                                        itemAmount = 0
                                    } else {
                                        isError = false
                                        errorText = ""
                                        itemAmount = parsed
                                    }
                                } else {
                                    input = next
                                    itemAmount = 0
                                    isError = true
                                    errorText = "(Invalid amount)"
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            shape = ShapeDefaults.ExtraLarge,
                            label = {
                                Text(text = "Amount$errorText")
                            },
                            isError = isError
                        )
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(
                                6.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            items(quickChips) { (label, delta) ->
                                AssistChip(
                                    onClick = {
                                        val next = (itemAmount + delta).coerceAtMost(MAX_ITEM_AMOUNT)
                                        itemAmount = next
                                        input = next.toString()
                                        isError = false
                                        errorText = ""
                                    },
                                    label = { Text(label) }
                                )
                            }
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextButton(
                                onClick = onDismissRequest,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text("Cancel")
                            }
                            TextButton(
                                onClick = { onConfirmation(itemAmount) },
                                modifier = Modifier.padding(8.dp),
                                enabled = itemAmount > 0
                            ) {
                                Text(confirmLabel)
                            }
                        }
                    }
                }
            }
        }
    }
}
