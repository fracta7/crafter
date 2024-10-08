package com.fracta7.crafter.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fracta7.crafter.ui.add_custom.item.AddCustomItem
import com.fracta7.crafter.ui.add_custom.recipe.AddCustomRecipe
import com.fracta7.crafter.ui.add_custom.recipe.type.AddCustomRecipeType
import com.fracta7.crafter.ui.main_activity.MainScreen
import com.fracta7.crafter.ui.crafting_screen.CraftingScreen
import com.fracta7.crafter.ui.theme.CrafterTheme

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    CrafterTheme(dynamicColor = false) {
        Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Route.Main) {
                composable<Route.Main> {
                    MainScreen(navController = navController)
                }
                composable<Route.Crafting> {
                    val args = it.toRoute<Route.Crafting>()
                    CraftingScreen(
                        navController = navController,
                        items = args.items,
                        amounts = args.amounts
                    )
                }
                composable<Route.AddCustomItem> {
                    AddCustomItem(navController = navController)
                }
                composable<Route.AddCustomRecipe> {
                    AddCustomRecipe(navController = navController)
                }
                composable<Route.AddCustomRecipeType> {
                    AddCustomRecipeType(navController = navController)
                }
            }
        }
    }
}