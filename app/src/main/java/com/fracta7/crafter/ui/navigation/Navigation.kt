package com.fracta7.crafter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fracta7.crafter.ui.crafting_screen.CraftingScreen
import com.fracta7.crafter.ui.main_activity.MainScreen
import com.fracta7.crafter.ui.root_crafting_screen.RootCraftingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Main) {
        composable<Route.Main> {
            MainScreen(navController = navController)
        }
        composable<Route.RootCrafting> {
            val args = it.toRoute<Route.RootCrafting>()
            RootCraftingScreen(
                navController = navController, items = args.items, amounts = args.amounts
            )
        }
        composable<Route.Crafting> {
            val args = it.toRoute<Route.Crafting>()
            CraftingScreen(
                navController = navController, itemID = args.item, amount = args.amount
            )
        }
    }
}