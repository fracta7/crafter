package com.fracta7.crafter.data.repository

import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val itemRegistry = ItemRegistry()
    private val recipeRegistry = RecipeRegistry()
    private val recipeTypes = recipeTypesInit()
    private val tags = tagsInit()

    /**
     * first we need to initialize all items and recipes
     * the order is important as RecipeRegistry requires
     * initialized and populated ItemRegistry instance for
     * recipes.
     */
    init {
        itemsInit().forEach {
            itemRegistry.addItem(it)
        }
        recipesInit().forEach {
            recipeRegistry.addRecipe(it)
        }
        specialRecipesInit().forEach {
            recipeRegistry.addRecipe(it)
        }
    }

    override fun getAllTags(): List<Category> {
        return tags
    }


    override fun itemRegistryProvider(): ItemRegistry {
        return itemRegistry
    }

    override fun recipeRegistryProvider(): RecipeRegistry {
        return recipeRegistry
    }

    override fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType {
        return recipeTypes.first { it.id == recipeTypeID }
    }
}