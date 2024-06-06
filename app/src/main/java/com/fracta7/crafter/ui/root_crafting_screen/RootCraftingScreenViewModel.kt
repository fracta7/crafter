package com.fracta7.crafter.ui.root_crafting_screen

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootCraftingScreenViewModel @Inject constructor(
    appRepository: AppRepository
) : ViewModel() {
    private val itemRegistry = appRepository.itemRegistryProvider()

    fun getItem(itemID: String): Item {
        return itemRegistry.getItem(itemID)!!
    }
}