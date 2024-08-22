package com.fracta7.crafter.ui.crafting_screen

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CraftingScreenViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry = appRepository.itemRegistryProvider()
    private val recipeRegistry = appRepository.recipeRegistryProvider()

    fun getItem(itemID: String): Item {
        return itemRegistry.getItem(itemID)!!
    }

    fun getItemRegistry(): ItemRegistry{
        return itemRegistry
    }

    fun getRecipeRegistry(): RecipeRegistry {
        return recipeRegistry
    }

    fun getAppRepository(): AppRepository {
        return appRepository
    }
}