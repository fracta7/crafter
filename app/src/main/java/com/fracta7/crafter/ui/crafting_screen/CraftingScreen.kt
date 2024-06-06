package com.fracta7.crafter.ui.crafting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
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
import com.fracta7.crafter.ui.elements.ItemElement
import com.fracta7.crafter.ui.helper.DrawItem
import com.fracta7.crafter.ui.navigation.RouteCrafting
import com.fracta7.crafter.ui.navigation.Screens
import com.fracta7.crafter.util.resourceAmount

@Composable
fun CraftingScreen(navController: NavController, itemID: String, amount: Int) {
    val viewModel = hiltViewModel<CraftingScreenViewModel>()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            val recipe = viewModel.getRecipe(itemID = itemID)
            val recipeItemIcon = viewModel.getBlockForRecipe(recipe.recipeType)
            val recipeName = viewModel.getRecipeName(recipe.recipeType)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                DrawItem(itemID = itemID)
                Text(
                    text = recipeName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }

        }
        val recipe = viewModel.getRecipe(itemID)
        recipe.requirements.forEach { (item, amountRecipeRequirement) ->
            item {
                val requiredAmount = amountRecipeRequirement * resourceAmount(result = recipe.resultQuantity, amountNeeded = amount)
                ItemElement(modifier = Modifier.fillMaxWidth().clickable {
                    if (item.craftable) {
                        //navController.navigate(Screens.CraftingScreen.withArgs(item.id, requiredAmount.toString()))
                        navController.navigate(RouteCrafting(item = item.id, amount = requiredAmount))
                    }
                }, item = item, amount = requiredAmount, preview = false)
                HorizontalDivider()
            }
        }
    }
}