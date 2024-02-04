package com.fracta7.crafter.ui.main_activity

import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

data class MainActivityState(
    val itemRegistry: ItemRegistry,
    val recipeRegistry: RecipeRegistry
)
