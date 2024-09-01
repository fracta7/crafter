package com.fracta7.crafter.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun AddNewDialog(
    onDismissRequest: () -> Unit,
    onAddNewItem: () -> Unit,
    onAddNewRecipe: () -> Unit,
    onAddNewRecipeType: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(shape = ShapeDefaults.ExtraLarge) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select an option",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(4.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        AddNewOptionListItem(
                            onClick = { onAddNewItem() },
                            iconID = R.drawable.rounded_cube_24,
                            text = "Add a new item"
                        )
                    }
                    item {
                        AddNewOptionListItem(
                            onClick = { onAddNewRecipe() },
                            iconID = R.drawable.round_device_hub_24,
                            text = "Add a new recipe"
                        )
                    }
                    item {
                        AddNewOptionListItem(
                            onClick = { onAddNewRecipeType() },
                            iconID = R.drawable.baseline_swap_horiz_24,
                            text = "Add a new recipe type"
                        )
                    }
                }
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

@Composable
fun AddNewOptionListItem(onClick: () -> Unit, iconID: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconID),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )
        Text(text = text)
    }
}

@Preview
@Composable
fun AddNewDialogPreview() {
    MaterialTheme(darkColorScheme()) {
        Surface {
            AddNewDialog(
                onDismissRequest = { },
                onAddNewItem = { },
                onAddNewRecipe = { },
                onAddNewRecipeType = { }
            )
        }
    }
}