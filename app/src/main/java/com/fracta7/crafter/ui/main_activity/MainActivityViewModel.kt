package com.fracta7.crafter.ui.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
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