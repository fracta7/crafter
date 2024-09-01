package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fracta7.crafter.R
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.helper.DrawItem

@Composable
fun ItemInfoDialog(item: Item, modifier: Modifier = Modifier, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        DrawItem(itemID = item.id)
                        Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 21.sp)
                    }
                    item {
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                    item {
                        Text(
                            text = "Item Information",
                            fontWeight = FontWeight.Medium,
                            fontSize = 19.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_id_24),
                                contentDescription = "item id",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(text = "Item ID: ")
                            Text(text = item.id, fontWeight = FontWeight.Bold)
                        }
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_item_name_24),
                                contentDescription = "item name",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(text = "Item Name: ")
                            Text(text = item.name, fontWeight = FontWeight.Bold)
                        }
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_stack_size_24),
                                contentDescription = "item stack size",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(text = "Stack Size: ")
                            Text(text = item.stackSize.toString(), fontWeight = FontWeight.Bold)
                        }
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_device_hub_24),
                                contentDescription = "decomposable?",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(text = "Decomposable: ")
                            Text(text = item.craftable.toString(), fontWeight = FontWeight.Bold)
                        }
                    }
                    item {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_tags_24),
                                contentDescription = "item tags",
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(text = "Tags: ")
                            item.tags.forEach {
                                Text(text = "$it ", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    item {
                        TextButton(onClick = { onDismissRequest() }) {
                            Text(text = "Dismiss")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemInfoDialogPreview() {
    val item = Item(
        id = "piston",
        name = "Piston",
        stackSize = 64,
        craftable = true,
        tags = listOf("redstone")
    )
    MaterialTheme(darkColorScheme()) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ItemInfoDialog(item = item, onDismissRequest = {})
        }
    }
}