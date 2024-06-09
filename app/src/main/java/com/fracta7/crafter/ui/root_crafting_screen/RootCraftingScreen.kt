package com.fracta7.crafter.ui.root_crafting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.navigation.Route
import com.fracta7.crafter.ui.theme.CrafterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootCraftingScreen(navController: NavController, items: List<String>, amounts: List<Int>) {
    val viewModel = hiltViewModel<RootCraftingScreenViewModel>()
    val itemsMap = items.zip(amounts).toMap()
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
                    Text(text = "Crafting List", modifier = Modifier.padding(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                        }, item = item, amount = amount, preview = false, stackSize = item.stackSize
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