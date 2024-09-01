package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID

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

    suspend fun addItem(item: Item)

    suspend fun removeItem(item: Item)

    suspend fun addRecipe(recipe: Recipe)

    suspend fun removeRecipe(recipe: Recipe)

    suspend fun addCategory(category: Category)

    suspend fun removeCategory(category: Category)

    suspend fun addRecipeType(recipeType: RecipeType)

    suspend fun removeRecipeType(recipeType: RecipeType)

    suspend fun deleteAll()
}