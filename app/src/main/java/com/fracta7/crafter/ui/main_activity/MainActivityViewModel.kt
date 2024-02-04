package com.fracta7.crafter.ui.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
}