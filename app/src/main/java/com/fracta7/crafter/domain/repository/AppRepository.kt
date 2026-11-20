package com.fracta7.crafter.domain.repository

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.TagRegistry
import kotlinx.coroutines.flow.StateFlow

interface AppRepository {
    val registryState: StateFlow<RegistrySnapshot>

    fun itemRegistryProvider(): ItemRegistry

    fun recipeRegistryProvider(): RecipeRegistry

    fun tagRegistryProvider(): TagRegistry

    fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType

    fun getAllTags(): List<Category>

    fun updateSnapshot(snapshot: RegistrySnapshot)
}
