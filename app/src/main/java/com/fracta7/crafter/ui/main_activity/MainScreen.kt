package com.fracta7.crafter.ui.main_activity

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.ui.elements.AddItemDialog
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainActivityViewModel>()
    var search by remember { mutableStateOf("") }
    var showDrawer by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var currentItemId by remember { mutableStateOf("") }
    var currentItemAmount by remember { mutableStateOf(0) }

    Scaffold(bottomBar = {
        BottomAppBar(actions = {
            IconButton(onClick = { showDrawer = !showDrawer }) {
                Icon(Icons.AutoMirrored.Rounded.List, contentDescription = "Icon List")
            }
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(
                    Route.RootCrafting(
                        items = viewModel.items.keys.map { it.id }.toList(),
                        amounts = viewModel.items.values.toList()
                    )
                )
            }) {
                Icon(Icons.Rounded.Done, contentDescription = "Icon Done")
            }
        }, windowInsets = BottomAppBarDefaults.windowInsets
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            OutlinedTextField(value = search, onValueChange = {
                search = it
            }, shape = ShapeDefaults.ExtraLarge, label = {
                Text(
                    text = "Item name"
                )
            }, modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(), leadingIcon = {
                Icon(Icons.Rounded.Search, contentDescription = "Search icon")
            }, trailingIcon = {
                IconButton(onClick = {
                    search = ""
                }) {
                    Icon(
                        Icons.Rounded.Clear, contentDescription = "Clear search query"
                    )
                }
            })

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.state.itemRegistry.getAll()
                    .filter { it.value.name.contains(search, ignoreCase = true) }
                    .forEach { (_, item) ->
                        item(key = item.id) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val spacing = if (viewModel.items.contains(item)) 0.9f else 1f
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth(0.85f)
                                ) {
                                    ItemElement(
                                        modifier = Modifier.fillMaxWidth(spacing), item = item
                                    )
                                    AnimatedVisibility(
                                        visible = viewModel.items.contains(item),
                                        enter = scaleIn(),
                                        exit = scaleOut()
                                    ) {
                                        Badge(modifier = Modifier.padding(start = 4.dp)) {
                                            Text(text = viewModel.items[item].toString())
                                        }
                                    }
                                }
                                IconButton(
                                    onClick = {
                                        currentItemId = item.id
                                        showAddDialog = !showAddDialog
                                    }, modifier = Modifier.padding(4.dp)
                                ) {
                                    Icon(
                                        Icons.Rounded.Add, contentDescription = "Add the item"
                                    )
                                }
                            }
                            HorizontalDivider()
                        }
                    }
            }

            if (showDrawer) {
                ModalBottomSheet(
                    onDismissRequest = { showDrawer = !showDrawer },
                    windowInsets = BottomSheetDefaults.windowInsets
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .defaultMinSize(minHeight = 120.dp)
                            .padding(paddingValues)
                    ) {
                        if (viewModel.items.isNotEmpty()) {
                            viewModel.items.forEach { (item, amount) ->
                                item {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        ItemElement(
                                            modifier = Modifier.fillMaxWidth(0.85f),
                                            item = item,
                                            amount = amount,
                                            preview = false
                                        )
                                        IconButton(onClick = {
                                            viewModel.removeItem(item)
                                        }) {
                                            Icon(
                                                Icons.Rounded.Delete,
                                                contentDescription = "Remove the item"
                                            )
                                        }
                                    }
                                    HorizontalDivider()
                                }
                            }
                        } else {
                            item {
                                Text(
                                    text = "There are no items in the list.",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(24.dp)
                                )
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(showAddDialog) {
                AddItemDialog(onDismissRequest = {
                    showAddDialog = !showAddDialog
                    currentItemAmount = 0
                },
                    onConfirmation = {
                        viewModel.addItemToList(
                            item = viewModel.state.itemRegistry.getItem(
                                currentItemId
                            )!!, amount = currentItemAmount
                        )
                        currentItemAmount = 0
                        showAddDialog = !showAddDialog
                    },
                    item = viewModel.state.itemRegistry.getItem(currentItemId)!!,
                    onGetInput = { currentItemAmount = it })
            }
        }
    }
}