package com.fracta7.crafter.data.repository

import com.fracta7.crafter.data.local.db.AppDatabase
import com.fracta7.crafter.data.local.db.mapper.toDomain
import com.fracta7.crafter.data.local.db.mapper.toEntity
import com.fracta7.crafter.data.local.itemsInit
import com.fracta7.crafter.data.local.recipeTypesInit
import com.fracta7.crafter.data.local.recipesInit
import com.fracta7.crafter.data.local.specialRecipesInit
import com.fracta7.crafter.data.local.tagsInit
import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    db: AppDatabase
) : AppRepository {
    private val itemRegistry = ItemRegistry()
    private val recipeRegistry = RecipeRegistry()
    private val recipeTypes: MutableList<RecipeType> = recipeTypesInit().toMutableList()
    private val tags: MutableList<Category> = tagsInit().toMutableList()
    private val itemsDao = db.itemDao()
    private val categoryDao = db.categoryDao()
    private val recipeDao = db.recipeDao()
    private val recipeTypeDao = db.recipeTypeDao()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

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

        // get custom data
        scope.launch {
            try {
                val customItems = itemsDao.getAll()
                if (customItems.isNotEmpty())
                    customItems.map { it.toDomain() }.forEach {
                        itemRegistry.addItem(it)
                    }
                val customRecipes = recipeDao.getAll()
                if (customRecipes.isNotEmpty())
                    customRecipes.map { it.toDomain() }.forEach {
                        recipeRegistry.addRecipe(it)
                    }
                val customRecipeTypes = recipeTypeDao.getAll()
                if (customRecipeTypes.isNotEmpty())
                    customRecipeTypes.map { it.toDomain() }.forEach {
                        recipeTypes.add(it)
                    }
                val customTags = categoryDao.getAll()
                if (customTags.isNotEmpty())
                    customTags.map { it.toDomain() }.forEach {
                        tags.add(it)
                    }
            } finally {
                job.complete()
            }
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

    override suspend fun addItem(item: Item) {
        itemsDao.insertAll(item.toEntity())
    }

    override suspend fun removeItem(item: Item) {
        itemsDao.delete(item.id)
    }

    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insertAll(recipe.toEntity())
    }

    override suspend fun removeRecipe(recipe: Recipe) {
        recipeDao.delete(recipe.toEntity())
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.insertAll(category.toEntity())
    }

    override suspend fun removeCategory(category: Category) {
        categoryDao.delete(category.toEntity())
    }

    override suspend fun addRecipeType(recipeType: RecipeType) {
        recipeTypeDao.insertAll(recipeType.toEntity())
    }

    override suspend fun removeRecipeType(recipeType: RecipeType) {
        recipeTypeDao.delete(recipeType.toEntity())
    }

    override suspend fun deleteAll() {
        itemsDao.deleteTable()
        recipeDao.deleteTable()
        recipeTypeDao.deleteTable()
        categoryDao.deleteTable()
    }
}