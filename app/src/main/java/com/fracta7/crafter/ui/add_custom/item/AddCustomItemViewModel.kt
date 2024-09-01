package com.fracta7.crafter.ui.add_custom.item

import androidx.lifecycle.ViewModel
import com.fracta7.crafter.domain.model.Item
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCustomItemViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel(){
    private val itemRegistry = appRepository.itemRegistryProvider()

    fun itemExists(itemID: ItemID): Boolean {
        return itemRegistry.getAll().containsKey(itemID)
    }
    fun itemNameExists(itemName: String): Boolean {
        return itemRegistry.getAll().values.any { it.name == itemName }
    }
    suspend fun addNewItem(item: Item) {
        appRepository.addItem(item)
    }
}