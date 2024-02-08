package com.fracta7.crafter.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fracta7.crafter.ui.elements.AddItemDialog
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.theme.CrafterTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrafterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainActivityViewModel>()
                    var search by remember { mutableStateOf("") }
                    var showDrawer by remember { mutableStateOf(false) }
                    var showAddDialog by remember { mutableStateOf(false) }
                    var currentItemId by remember { mutableStateOf("") }
                    var currentItemAmount by remember { mutableStateOf(0) }
                    Scaffold(
                        bottomBar = {
                            BottomAppBar(
                                actions = {
                                    IconButton(onClick = { showDrawer = !showDrawer }) {
                                        Icon(Icons.Rounded.List, contentDescription = "Icon List")
                                    }
                                },
                                floatingActionButton = {
                                    FloatingActionButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Rounded.Done, contentDescription = "Icon Done")
                                    }
                                },
                                windowInsets = BottomAppBarDefaults.windowInsets
                            )
                        }
                    ) { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(paddingValues)
                        ) {
                            val coroutineScope = rememberCoroutineScope()
                            OutlinedTextField(
                                value = search,
                                onValueChange = {
                                    search = it
                                },
                                shape = ShapeDefaults.ExtraLarge,
                                label = {
                                    Text(
                                        text = "Item name"
                                    )
                                },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                leadingIcon = {
                                    Icon(Icons.Rounded.Search, contentDescription = "Search icon")
                                },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        search = ""
                                    }) {
                                        Icon(
                                            Icons.Rounded.Clear,
                                            contentDescription = "Clear search query"
                                        )
                                    }
                                }
                            )

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
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
                                                Row(verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.fillMaxWidth(0.85f)) {
                                                    ItemElement(modifier = Modifier.fillMaxWidth(spacing), item = item)
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
                                                },
                                                    modifier = Modifier.padding(4.dp)
                                                ) {
                                                    Icon(
                                                        Icons.Rounded.Add,
                                                        contentDescription = "Add the item"
                                                    )
                                                }
                                            }
                                            Divider()
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
                                                            modifier = Modifier,
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
                                                    Divider()
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
                                AddItemDialog(
                                    onDismissRequest = {
                                        showAddDialog = !showAddDialog
                                        currentItemAmount = 0
                                    },
                                    onConfirmation = {
                                        viewModel.addItemToList(
                                            item = viewModel.state.itemRegistry.getItem(
                                                currentItemId
                                            )!!,
                                            amount = currentItemAmount
                                        )
                                        currentItemAmount = 0
                                        showAddDialog = !showAddDialog
                                    },
                                    item = viewModel.state.itemRegistry.getItem(currentItemId)!!,
                                    onGetInput = { currentItemAmount = it }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}