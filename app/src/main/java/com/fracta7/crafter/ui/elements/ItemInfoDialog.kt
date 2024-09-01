package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

data class Info(
    val iconID: Int,
    val infoDescription: String,
    val info: String
)

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
                    getInfoList(item).forEach {
                        item {
                            InfoRow(
                                iconID = it.iconID,
                                infoDescription = it.infoDescription,
                                info = it.info
                            )
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

@Composable
fun InfoRow(iconID: Int, infoDescription: String, info: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconID),
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
            Text(text = infoDescription)
        }
        Text(text = info, fontWeight = FontWeight.Bold, maxLines = 1, modifier = Modifier.basicMarquee(iterations = 1000))
    }
}

fun getInfoList(item: Item): List<Info> {
    var tags = ""
    val tagsSize = item.tags.size
    for (i in 0..<tagsSize) {
        tags += if (i == (tagsSize - 1)) {
            item.tags[i]
        } else {
            "${item.tags[i]}, "
        }
    }
    val infos = listOf(
        Info(
            iconID = R.drawable.round_id_24,
            infoDescription = "Item ID: ",
            info = item.id
        ),
        Info(
            iconID = R.drawable.round_item_name_24,
            infoDescription = "Item name: ",
            info = item.name
        ),
        Info(
            iconID = R.drawable.round_stack_size_24,
            infoDescription = "Stack size: ",
            info = item.stackSize.toString()
        ),
        Info(
            iconID = R.drawable.round_device_hub_24,
            infoDescription = "Decomposable: ",
            info = item.decomposable.toString()
        ),
        Info(
            iconID = R.drawable.round_tags_24,
            infoDescription = "Tags: ",
            info = tags
        )
    )
    return infos
}

@Preview
@Composable
fun ItemInfoDialogPreview() {
    val item = Item(
        id = "piston",
        name = "Piston",
        stackSize = 64,
        decomposable = true,
        tags = listOf("redstone")
    )
    MaterialTheme(darkColorScheme()) {
        Surface(color = MaterialTheme.colorScheme.background) {
            ItemInfoDialog(item = item, onDismissRequest = {})
        }
    }
}