package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
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

@Composable
fun AddItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    item: Item,
    onGetInput: (Int) -> Unit
) {
    var input by remember { mutableStateOf("") }
    var itemAmount by remember { mutableIntStateOf(0) }
    var isError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        DrawItem(modifier = Modifier.padding(4.dp), itemID = item.id, iconSize = 72.dp, custom = item.custom)
                    }
                    item {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.basicMarquee(),
                            maxLines = 1
                        )
                        Text(
                            text = "Add this item to the crafting list",
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = input,
                            onValueChange = {
                                if (it.isBlank()) {
                                    input = it
                                    isError = true
                                    errorText = "(Amount cannot be empty)"
                                    itemAmount = 0
                                } else if (it.isDigitsOnly()) {
                                    input = it
                                    isError = false
                                    errorText = ""
                                    itemAmount = input.toInt()
                                    onGetInput(itemAmount)
                                } else {
                                    input = it
                                    itemAmount = 0
                                    isError = true
                                    errorText = "(Invalid amount)"
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            shape = ShapeDefaults.ExtraLarge,
                            label = {
                                Text(text = "Amount$errorText")
                            },
                            isError = isError
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            TextButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.padding(8.dp),
                            ) {
                                Text("Cancel")
                            }
                            TextButton(
                                onClick = { onConfirmation() },
                                modifier = Modifier.padding(8.dp),
                                enabled = itemAmount > 0
                            ) {
                                Text("Add")
                            }
                        }
                    }
                }
            }
        }
    }
}