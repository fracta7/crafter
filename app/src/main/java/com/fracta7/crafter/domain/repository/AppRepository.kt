package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.TagID
import com.fracta7.crafter.domain.model.TagName

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

    fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType

    fun getAllTags(): List<Category>
}