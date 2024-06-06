package com.fracta7.crafter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route{
    @Serializable
    object Main : Route()

    @Serializable
    data class RootCrafting(
        val items: List<String>,
        val amounts: List<Int>
    )

    @Serializable
    data class Crafting(
        val item: String,
        val amount: Int
    )
}