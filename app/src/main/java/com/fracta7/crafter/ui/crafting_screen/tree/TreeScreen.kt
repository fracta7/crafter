package com.fracta7.crafter.ui.crafting_screen.tree

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.util.buildRecipeTree

@Composable
fun RecipeTreeScreen(navController: NavController, itemID: ItemID, amount: Int) {
    val viewModel = hiltViewModel<TreeCraftingScreenViewModel>()
    val recipeTree = buildRecipeTree(
        itemID,
        amount,
        viewModel.itemRegistry,
        viewModel.getAppRepository().recipeRegistryProvider()
    )
    Column(modifier = Modifier.padding(16.dp)) {
        View(recipeTree.children, viewModel.getAppRepository())
    }
}