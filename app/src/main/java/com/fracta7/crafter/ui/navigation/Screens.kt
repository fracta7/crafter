package com.fracta7.crafter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route{
    @Serializable
    data object Main : Route()

    @Serializable
    data class Crafting(
        val items: List<String>,
        val amounts: List<Int>
    )
}