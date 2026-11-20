package com.fracta7.crafter.ui.crafting_screen.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.elements.DrawItem

private const val MAX_OWNED_AMOUNT = 999_999

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OwnedQuantityDialog(
    item: Item,
    requiredAmount: Int,
    currentOwned: Int,
    onDismissRequest: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var input by remember(item.id, currentOwned) {
        mutableStateOf(if (currentOwned > 0) currentOwned.toString() else "0")
    }
    var parsedAmount by remember(item.id, currentOwned) {
        mutableIntStateOf(currentOwned.coerceIn(0, MAX_OWNED_AMOUNT))
    }
    var errorText by remember(item.id) { mutableStateOf<String?>(null) }

    fun applyAmount(nextAmount: Int) {
        val clamped = nextAmount.coerceIn(0, MAX_OWNED_AMOUNT)
        parsedAmount = clamped
        input = clamped.toString()
        errorText = null
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DrawItem(itemID = item.id, iconSize = 64.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee()
                )
                Text(
                    text = "Already have (Required: $requiredAmount)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                )

                OutlinedTextField(
                    value = input,
                    onValueChange = { next ->
                        input = next
                        when {
                            next.isBlank() -> {
                                parsedAmount = 0
                                errorText = "Enter 0 or more"
                            }
                            !next.isDigitsOnly() -> {
                                errorText = "Invalid number"
                            }
                            else -> {
                                val value = next.toIntOrNull()
                                if (value == null || value > MAX_OWNED_AMOUNT) {
                                    errorText = "Max $MAX_OWNED_AMOUNT"
                                } else {
                                    parsedAmount = value
                                    errorText = null
                                }
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = ShapeDefaults.ExtraLarge,
                    label = { Text(errorText ?: "Owned amount") },
                    isError = errorText != null,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AssistChip(
                        onClick = { applyAmount(0) },
                        label = { Text("0") }
                    )
                    AssistChip(
                        onClick = { applyAmount(parsedAmount + 1) },
                        label = { Text("+1") }
                    )
                    if (item.stackSize > 1) {
                        AssistChip(
                            onClick = { applyAmount(parsedAmount + 16) },
                            label = { Text("+16") }
                        )
                        AssistChip(
                            onClick = { applyAmount(parsedAmount + item.stackSize) },
                            label = { Text("+${item.stackSize}") }
                        )
                    }
                    AssistChip(
                        onClick = { applyAmount(requiredAmount) },
                        label = { Text("All ($requiredAmount)") }
                    )
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
                        enabled = errorText == null,
                        onClick = {
                            onConfirm(parsedAmount)
                            onDismissRequest()
                        }
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}
