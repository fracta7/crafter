package com.fracta7.crafter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fracta7.crafter.ui.crafting_screen.CraftingScreen
import com.fracta7.crafter.ui.main_activity.MainScreen
import com.fracta7.crafter.ui.root_crafting_screen.RootCraftingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screens.CraftingScreen.route + "/{item_id}/{amount}",
            arguments = listOf(
                navArgument("item_id") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("amount"){
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            CraftingScreen(
                navController = navController,
                itemID = entry.arguments!!.getString("item_id")!!,
                amount = entry.arguments!!.getInt("amount")!!
            )
        }
        composable(
            route = Screens.RootCraftingScreen.route + "/{ids}/{amounts}",
            arguments = listOf(
                navArgument("ids"){
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("amounts"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ){entry ->
            RootCraftingScreen(
                navController = navController,
                ids = entry.arguments!!.getString("ids")!!,
                amounts = entry.arguments!!.getString("amounts")!!
            )
        }
    }
}