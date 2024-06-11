package com.fracta7.crafter.ui.root_crafting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.navigation.Route
import com.fracta7.crafter.ui.theme.CrafterTheme
import com.fracta7.crafter.util.convertIdMapToItemMap
import com.fracta7.crafter.util.decomposeItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootCraftingScreen(navController: NavController, items: List<String>, amounts: List<Int>) {
    val viewModel = hiltViewModel<RootCraftingScreenViewModel>()
    val itemsMap = items.zip(amounts).toMap()
    var rawItems by remember { mutableStateOf(mapOf<Item, Int>()) }
    var leftOvers by remember { mutableStateOf(mapOf<Item, Int>()) }
    var itemsConverted by remember { mutableStateOf(mapOf<Item, Int>()) }
    val coroutineScope = rememberCoroutineScope()
    CrafterTheme(dynamicColor = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold(topBar = {
                TopAppBar(title = { Text(text = "Crafter") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                        }
                    })
            }
            ) { padding ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    Text(text = "Raw Items", modifier = Modifier.padding(10.dp))
                    OutlinedCard(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 4.dp)
                        ) {
                            coroutineScope.launch {
                                itemsConverted = convertIdMapToItemMap(
                                    itemsId = itemsMap,
                                    itemRegistry = viewModel.getItemRegistry()
                                )
                                val result = decomposeItems(
                                    itemsToDecompose = itemsConverted,
                                    recipeRegistry = viewModel.getRecipeRegistry()
                                )
                                rawItems = result.first
                                leftOvers = result.second

                            }
                            rawItems.forEach { (item, amount) ->
                                item {
                                    ItemElement(
                                        modifier = Modifier.fillMaxWidth(),
                                        item = item,
                                        amount = amount,
                                        preview = false,
                                        stackSize = item.stackSize
                                    )
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp))
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.padding(10.dp))
                                Text(text = "Left-overs", modifier = Modifier.padding(10.dp))
                            }
                            leftOvers.forEach { (item, amount) ->
                                item{
                                    ItemElement(
                                        modifier = Modifier.fillMaxWidth(),
                                        item = item,
                                        amount = amount,
                                        preview = false,
                                        stackSize = item.stackSize
                                    )
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "Crafting List", modifier = Modifier.padding(10.dp))
                    OutlinedCard(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 4.dp)
                        ) {
                            itemsMap.forEach { (itemID, amount) ->
                                item {
                                    val item = viewModel.getItem(itemID)
                                    ItemElement(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {

                                                if (item.craftable) {
                                                    //navController.navigate(Screens.CraftingScreen.withArgs(item.id, requiredAmount.toString()))
                                                    navController.navigate(
                                                        Route.Crafting(
                                                            item = item.id,
                                                            amount = amount
                                                        )
                                                    )
                                                }
                                            },
                                        item = item,
                                        amount = amount,
                                        preview = false,
                                        stackSize = item.stackSize
                                    )
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp))
                                }
                            }
                        }
                    }

                }

            }
        }
    }
}