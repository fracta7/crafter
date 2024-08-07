package com.fracta7.crafter.ui.crafting_screen

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CraftingScreenViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry = appRepository.itemRegistryProvider()
    private val recipeRegistry = appRepository.recipeRegistryProvider()

    fun getRecipe(itemID: String): Recipe {
        return recipeRegistry.getRecipesByResult(itemID)[0]
    }
    fun getItemById(itemId: String): Item{
        return itemRegistry.getItem(id = itemId)!!
    }
    fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType {
        return appRepository.getRecipeType(recipeTypeID)
    }
    fun getRepository(): AppRepository {
        return appRepository
    }
}