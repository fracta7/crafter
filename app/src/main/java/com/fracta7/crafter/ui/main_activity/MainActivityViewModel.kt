package com.fracta7.crafter.ui.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry: ItemRegistry
    private val recipeRegistry: RecipeRegistry
    val items = mutableStateMapOf<Item,Int>()

    init {
        itemRegistry = appRepository.itemRegistryProvider()
        recipeRegistry = appRepository.recipeRegistryProvider()
    }

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
}