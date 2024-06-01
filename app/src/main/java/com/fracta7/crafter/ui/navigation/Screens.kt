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
object ScreenMain

@Serializable
data class ScreenRootCrafting(
    val items: Map<Item, Int>
)

@Serializable
data class ScreenCrafting(
    val item: Item,
    val amount: Int
)