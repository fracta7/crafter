package com.fracta7.crafter.ui.crafting_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.ui.elements.CraftingElement
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.theme.CrafterTheme
import com.fracta7.crafter.util.decomposeItems
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftingScreen(navController: NavController, items: List<ItemID>, amounts: List<Int>) {
    val viewModel = hiltViewModel<CraftingScreenViewModel>()
    val itemsMap = items.zip(amounts).toMap()
    var rawItems by remember { mutableStateOf(mapOf<ItemID, Int>()) }
    var leftOvers by remember { mutableStateOf(mapOf<ItemID, Int>()) }
    var rawItemsVisible by remember { mutableStateOf(false) }
    var leftoversVisible by remember { mutableStateOf(false) }
    var craftingVisible by remember { mutableStateOf(true) }
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
                    OutlinedCard(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .clickable { rawItemsVisible = !rawItemsVisible }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Raw Items",
                                modifier = Modifier
                                    .padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            val icon =
                                if (rawItemsVisible) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                            Icon(
                                icon,
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        coroutineScope.launch {
                            val result = decomposeItems(
                                itemsToDecompose = itemsMap,
                                recipeRegistry = viewModel.getRecipeRegistry(),
                                itemRegistry = viewModel.getItemRegistry()
                            )
                            rawItems = result.first
                            leftOvers = result.second
                        }
                        AnimatedVisibility(visible = rawItemsVisible) {

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.4f)
                                    .padding(10.dp, 4.dp)
                            ) {
                                rawItems.forEach { (itemID, amount) ->
                                    val item = viewModel.getItem(itemID)
                                    item {
                                        ItemElement(
                                            modifier = Modifier.fillMaxWidth(),
                                            item = item,
                                            amount = amount,
                                            preview = false,
                                            stackSize = item.stackSize
                                        )
                                        Divider(modifier = Modifier.padding(horizontal = 4.dp))
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    if (leftOvers.isNotEmpty()) {
                        OutlinedCard(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .fillMaxWidth()
                                .clickable { leftoversVisible = !leftoversVisible }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Left-overs",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                val icon =
                                    if (leftoversVisible) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                                Icon(
                                    icon,
                                    contentDescription = null,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                            AnimatedVisibility(visible = leftoversVisible) {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.4f)
                                        .padding(10.dp, 4.dp)
                                ) {
                                    leftOvers.forEach { (itemID, amount) ->
                                        item {
                                            val item = viewModel.getItem(itemID)
                                            ItemElement(
                                                modifier = Modifier.fillMaxWidth(),
                                                item = item,
                                                amount = amount,
                                                preview = false,
                                                stackSize = item.stackSize
                                            )
                                            Divider(
                                                modifier = Modifier.padding(
                                                    horizontal = 4.dp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (leftOvers.isNotEmpty()) Spacer(modifier = Modifier.padding(4.dp))
                    OutlinedCard(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { craftingVisible = !craftingVisible }
                        ) {
                            Text(
                                text = "Crafting List",
                                modifier = Modifier
                                    .padding(10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            val icon =
                                if (craftingVisible) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                            Icon(
                                icon,
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        AnimatedVisibility(visible = craftingVisible) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp, 4.dp)
                            ) {
                                itemsMap.forEach { (itemID, amount) ->
                                    item {
                                        val item = viewModel.getItem(itemID)
                                        CraftingElement(item = item, amount = amount, appRepository = viewModel.getAppRepository())
//                                        ItemElement(
//                                            modifier = Modifier
//                                                .fillMaxWidth()
//                                                .clickable {
//
//                                                    if (item.craftable) {
//                                                        //navController.navigate(Screens.CraftingScreen.withArgs(item.id, requiredAmount.toString()))
//                                                        navController.navigate(
//                                                            Route.Crafting(
//                                                                item = item.id,
//                                                                amount = amount
//                                                            )
//                                                        )
//                                                    }
//                                                },
//                                            item = item,
//                                            amount = amount,
//                                            preview = false,
//                                            stackSize = item.stackSize
//                                        )
                                        Divider(
                                            modifier = Modifier.padding(
                                                horizontal = 4.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
