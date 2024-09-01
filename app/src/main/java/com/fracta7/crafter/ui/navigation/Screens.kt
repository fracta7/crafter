package com.fracta7.crafter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Main : Route()

    @Serializable
    data class Crafting(
        val items: List<String>,
        val amounts: List<Int>
    )

    @Serializable
    data object AddCustomItem : Route()

    @Serializable
    data object AddCustomRecipe : Route()

    @Serializable
    data object AddCustomRecipeType : Route()
}