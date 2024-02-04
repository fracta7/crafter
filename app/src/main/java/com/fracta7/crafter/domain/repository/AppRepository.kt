package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry

interface AppRepository {
    /**
     * Provides an instance of ItemRegistry class that holds all items.
     * @return ItemRegistry instance
     */
    fun itemRegistryProvider(): ItemRegistry

    /**
     * Provides an instance of RecipeRegistry class that holds all recipes for items.
     * @return RecipeRegistry instance.
     */
    fun recipeRegistryProvider(): RecipeRegistry
}