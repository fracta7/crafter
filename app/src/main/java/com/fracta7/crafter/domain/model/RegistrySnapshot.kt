package com.fracta7.crafter.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class RegistryFileChecksum(
    val sha256: String,
    val bytes: Long
)

@Immutable
@Serializable
data class RegistryManifest(
    val schemaVersion: Int = 1,
    val mcVersion: String = "26.3",
    val registryVersion: Int = 1,
    val generatedAt: String = "",
    val iconBaseUrl: String = "",
    val files: Map<String, RegistryFileChecksum> = emptyMap()
)

enum class RegistrySource(val label: String) {
    BUNDLED("Bundled offline"),
    OTA_CACHE("Updated over-the-air")
}

@Immutable
data class RegistrySnapshot(
    val manifest: RegistryManifest,
    val source: RegistrySource,
    val itemRegistry: ItemRegistry,
    val recipeRegistry: RecipeRegistry,
    val tagRegistry: TagRegistry,
    val categories: List<Category>,
    val recipeTypes: Map<RecipeTypeID, RecipeType>,
    val recipeCount: Int = 0
)
