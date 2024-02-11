package com.fracta7.crafter.ui.navigation

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