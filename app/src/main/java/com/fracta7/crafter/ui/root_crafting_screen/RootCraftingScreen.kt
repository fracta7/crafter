package com.fracta7.crafter.ui.root_crafting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.helper.DrawItem
import com.fracta7.crafter.ui.navigation.RouteCrafting
import com.fracta7.crafter.util.resourceAmount

@Composable
fun RootCraftingScreen(navController: NavController, items: List<String>, amounts: List<Int>) {
    val viewModel = hiltViewModel<RootCraftingScreenViewModel>()
    val itemsMap = items.zip(amounts).toMap()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsMap.forEach{ (itemID, amount)->
            item {
                val item = viewModel.getItem(itemID)
                ItemElement(modifier = Modifier.fillMaxWidth().clickable {

                    if (item.craftable) {
                        //navController.navigate(Screens.CraftingScreen.withArgs(item.id, requiredAmount.toString()))
                        navController.navigate(RouteCrafting(item = item.id, amount = amount))
                    }
                }, item = item, amount = amount, preview = false)
                HorizontalDivider()
            }
        }
    }
}