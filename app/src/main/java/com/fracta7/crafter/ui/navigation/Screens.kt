package com.fracta7.crafter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Catalog : Route()

    @Serializable
    data object Projects : Route()

    @Serializable
    data object Settings : Route()

    @Serializable
    data class Crafting(val projectId: Long) : Route()
}
