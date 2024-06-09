package com.fracta7.crafter.ui.crafting_screen

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.Recipe
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CraftingScreenViewModel @Inject constructor(
    appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry = appRepository.itemRegistryProvider()
    private val recipeRegistry = appRepository.recipeRegistryProvider()

    fun getRecipe(itemID: String): Recipe {
        return recipeRegistry.getRecipesByResult(itemRegistry.getItem(itemID)!!)[0]
    }
    fun getBlockForRecipe(recipeType: RecipeType): Item{
        return when (recipeType) {
            RecipeType.Crafting -> itemRegistry.getItem("crafting_table")!!
            RecipeType.Cutting -> itemRegistry.getItem("stonecutter")!!
            RecipeType.Oxidation -> itemRegistry.getItem("oxidized_copper")!!
            RecipeType.Smelting -> itemRegistry.getItem("furnace")!!
            RecipeType.Stripping -> itemRegistry.getItem("diamond_axe")!!
            RecipeType.Watering -> itemRegistry.getItem("water_bucket")!!
            RecipeType.Waxing -> itemRegistry.getItem("honeycomb")!!
            RecipeType.Smithing -> itemRegistry.getItem("smithing_table")!!
        }
    }
    fun getRecipeName(recipeType: RecipeType): String{
        return when (recipeType) {
            RecipeType.Crafting -> "Crafting"
            RecipeType.Cutting -> "Cutting"
            RecipeType.Oxidation -> "Oxidation"
            RecipeType.Smelting -> "Smelting"
            RecipeType.Stripping -> "Stripping"
            RecipeType.Watering -> "Watering"
            RecipeType.Waxing -> "Waxing"
            RecipeType.Smithing -> "Smithing"
        }
    }
    fun getItemById(itemId: String): Item{
        return itemRegistry.getItem(id = itemId)!!
    }
}