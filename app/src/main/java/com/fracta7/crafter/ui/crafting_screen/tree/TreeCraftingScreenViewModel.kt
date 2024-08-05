package com.fracta7.crafter.ui.crafting_screen.tree

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TreeCraftingScreenViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {
    val itemRegistry = appRepository.itemRegistryProvider()

    fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType {
        return appRepository.getRecipeType(recipeTypeID)
    }
    fun getAppRepository(): AppRepository {
        return appRepository
    }
}