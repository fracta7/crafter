package com.fracta7.crafter.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fracta7.crafter.ui.crafting_screen.CraftingScreen
import com.fracta7.crafter.ui.main_activity.MainScreen
import com.fracta7.crafter.ui.projects_screen.ProjectsScreen
import com.fracta7.crafter.ui.settings_screen.SettingsScreen

private data class TopLevelTab(
    val route: Route,
    val label: String,
    val icon: ImageVector
)

private val TOP_LEVEL_TABS = listOf(
    TopLevelTab(Route.Catalog, "Catalog", Icons.Rounded.Search),
    TopLevelTab(Route.Projects, "Projects", Icons.AutoMirrored.Rounded.List),
    TopLevelTab(Route.Settings, "Settings", Icons.Rounded.Settings)
)

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.let { dest ->
        dest.hasRoute<Route.Catalog>() ||
            dest.hasRoute<Route.Projects>() ||
            dest.hasRoute<Route.Settings>()
    } ?: true

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    TOP_LEVEL_TABS.forEach { tab ->
                        val selected = when (tab.route) {
                            Route.Catalog -> currentDestination?.hasRoute<Route.Catalog>() == true
                            Route.Projects -> currentDestination?.hasRoute<Route.Projects>() == true
                            Route.Settings -> currentDestination?.hasRoute<Route.Settings>() == true
                            is Route.Crafting -> false
                        }
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Catalog,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<Route.Catalog> {
                MainScreen(navController = navController)
            }
            composable<Route.Projects> {
                ProjectsScreen(navController = navController)
            }
            composable<Route.Settings> {
                SettingsScreen()
            }
            composable<Route.Crafting> {
                val args = it.toRoute<Route.Crafting>()
                CraftingScreen(
                    navController = navController,
                    projectId = args.projectId
                )
            }
        }
    }
}
