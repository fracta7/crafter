package com.fracta7.crafter.data.repository

import android.app.Application
import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.domain.model.Category
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RecipeType
import com.fracta7.crafter.domain.model.RecipeTypeID
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.TagRegistry
import com.fracta7.crafter.domain.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    app: Application,
    registryLoader: RegistryLoader
) : AppRepository {

    private val _registryState = MutableStateFlow(
        registryLoader.loadStartupSnapshot(
            assetManager = app.assets,
            cacheDir = File(app.filesDir, "registry_cache")
        )
    )
    override val registryState: StateFlow<RegistrySnapshot> = _registryState.asStateFlow()

    override fun getAllTags(): List<Category> = _registryState.value.categories

    override fun itemRegistryProvider(): ItemRegistry = _registryState.value.itemRegistry

    override fun recipeRegistryProvider(): RecipeRegistry = _registryState.value.recipeRegistry

    override fun tagRegistryProvider(): TagRegistry = _registryState.value.tagRegistry

    override fun getRecipeType(recipeTypeID: RecipeTypeID): RecipeType {
        return _registryState.value.recipeTypes[recipeTypeID]
            ?: RecipeType(
                id = recipeTypeID,
                name = recipeTypeID.replace('_', ' ').replaceFirstChar { it.uppercase() },
                item = "crafting_table"
            )
    }

    override fun updateSnapshot(snapshot: RegistrySnapshot) {
        _registryState.value = snapshot
    }
}
