package com.fracta7.crafter.ui.navigation

import com.fracta7.crafter.domain.model.Item
import kotlinx.serialization.Serializable

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object CraftingScreen : Screens("crafting_screen")
    object RootCraftingScreen : Screens("root_crafting_screen")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

@Serializable
object RouteMain

@Serializable
data class RouteRootCrafting(
    val items: List<String>,
    val amounts: List<Int>
)

@Serializable
data class RouteCrafting(
    val item: String,
    val amount: Int
)