package com.fracta7.crafter.ui.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry: ItemRegistry = appRepository.itemRegistryProvider()
    private val recipeRegistry: RecipeRegistry = appRepository.recipeRegistryProvider()
    val items = mutableStateMapOf<Item,Int>()

    var state by mutableStateOf(
        MainActivityState(
            itemRegistry = itemRegistry,
            recipeRegistry = recipeRegistry
        )
    )

    fun addItemToList(item: Item, amount: Int) {
        if (items[item]!= null) {
            items[item] = items[item]!! + amount
        } else {
            items[item] = amount
        }
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun getCategories(): List<Category> {
        return appRepository.getAllTags()
    }
}