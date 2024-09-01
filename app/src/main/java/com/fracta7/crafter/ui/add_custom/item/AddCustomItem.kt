package com.fracta7.crafter.ui.add_custom.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.navigation.Route
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomItem(navController: NavController) {
    var itemId by remember { mutableStateOf("") }
    var itemName by remember { mutableStateOf("") }
    var stackSize by remember { mutableFloatStateOf(64f) }
    var decomposable by remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<AddCustomItemViewModel>()
    val isItemIdError by remember { mutableStateOf(viewModel.itemExists(itemId)) }
    val isItemNameError by remember { mutableStateOf(viewModel.itemNameExists(itemName)) }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add custom item") }, navigationIcon = {
                IconButton(
                    onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                }
            })
        },
        floatingActionButton = {
            if(
                !isItemIdError && !isItemNameError && itemId.isNotBlank() && itemName.isNotBlank()
            ) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        viewModel.addNewItem(
                            Item(
                                id = itemId,
                                name = itemName,
                                stackSize = stackSize.toInt(),
                                decomposable = decomposable,
                                custom = true
                            )
                        )
                    }
                    navController.navigate(Route.Main)
                }) {
                    Icon(Icons.Rounded.Done, contentDescription = "Add")
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp)
            ) {
                val idLabel = if (isItemIdError) "Item ID (Already exists)" else "Item ID"
                Text(text = "Item information", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = itemId,
                    onValueChange = { itemId = it },
                    label = { Text(text = idLabel) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = ShapeDefaults.ExtraLarge,
                    isError = viewModel.itemExists(itemId)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                val nameLabel = if (isItemNameError) "Item Name (Already exists)" else "Item Name"
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text(text = nameLabel) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = ShapeDefaults.ExtraLarge
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Stack size: ${stackSize.toInt()}")
                    Slider(
                        value = stackSize,
                        onValueChange = { stackSize = it },
                        steps = 64,
                        valueRange = 1f..64f,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable { decomposable = !decomposable }
                ) {
                    Text(text = "Decomposable")
                    Switch(
                        checked = decomposable,
                        onCheckedChange = { decomposable = !decomposable },
                        thumbContent = {
                            if (decomposable)
                                Icon(
                                    Icons.Rounded.Done,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize)
                                )
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCustomItemPreview() {
    MaterialTheme(darkColorScheme()) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AddCustomItem(navController = rememberNavController())
        }
    }
}