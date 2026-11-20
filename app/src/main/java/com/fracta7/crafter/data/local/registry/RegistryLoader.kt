package com.fracta7.crafter.data.local.registry

import android.content.res.AssetManager
import com.fracta7.crafter.domain.model.ItemID
import com.fracta7.crafter.domain.model.ItemRegistry
import com.fracta7.crafter.domain.model.RecipeRegistry
import com.fracta7.crafter.domain.model.RegistryManifest
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.model.TagID
import com.fracta7.crafter.domain.model.TagRegistry
import kotlinx.serialization.json.Json
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

const val SUPPORTED_REGISTRY_SCHEMA_VERSION = 1

@Singleton
class RegistryLoader @Inject constructor() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun loadStartupSnapshot(
        assetManager: AssetManager,
        cacheDir: File
    ): RegistrySnapshot {
        val bundled = loadFromAssets(assetManager)
        val cachedManifestFile = File(cacheDir, "manifest.json")
        if (!cachedManifestFile.isFile) {
            return bundled
        }

        val cached = runCatching {
            val manifest = parseManifest(cachedManifestFile.readText(Charsets.UTF_8))
            if (
                manifest.schemaVersion != SUPPORTED_REGISTRY_SCHEMA_VERSION ||
                manifest.registryVersion <= bundled.manifest.registryVersion
            ) {
                return@runCatching null
            }
            if (!verifyDirectoryChecksums(cacheDir, manifest)) {
                return@runCatching null
            }
            loadFromDirectory(cacheDir, RegistrySource.OTA_CACHE)
        }.getOrNull()

        return cached ?: bundled
    }

    fun loadFromAssets(
        assetManager: AssetManager,
        assetFolder: String = "registry"
    ): RegistrySnapshot {
        fun readAsset(fileName: String): String =
            assetManager.open("$assetFolder/$fileName").bufferedReader(Charsets.UTF_8).use { it.readText() }

        return buildSnapshot(
            source = RegistrySource.BUNDLED,
            manifestText = readAsset("manifest.json"),
            itemsText = readAsset("items.json"),
            recipesText = readAsset("recipes.json"),
            tagsText = readAsset("item_tags.json"),
            categoriesText = readAsset("categories.json"),
            recipeTypesText = readAsset("recipe_types.json")
        )
    }

    fun loadFromDirectory(
        directory: File,
        source: RegistrySource
    ): RegistrySnapshot {
        fun readFile(fileName: String): String =
            File(directory, fileName).readText(Charsets.UTF_8)

        return buildSnapshot(
            source = source,
            manifestText = readFile("manifest.json"),
            itemsText = readFile("items.json"),
            recipesText = readFile("recipes.json"),
            tagsText = readFile("item_tags.json"),
            categoriesText = readFile("categories.json"),
            recipeTypesText = readFile("recipe_types.json")
        )
    }

    fun parseManifest(raw: String): RegistryManifest =
        json.decodeFromString<RegistryManifest>(raw)

    fun verifyDirectoryChecksums(directory: File, manifest: RegistryManifest): Boolean {
        if (manifest.files.isEmpty()) return false
        for ((fileName, expected) in manifest.files) {
            val file = File(directory, fileName)
            if (!file.isFile) return false
            val bytes = file.readBytes()
            if (bytes.size.toLong() != expected.bytes) return false
            if (!sha256Hex(bytes).equals(expected.sha256, ignoreCase = true)) return false
        }
        return true
    }

    private fun buildSnapshot(
        source: RegistrySource,
        manifestText: String,
        itemsText: String,
        recipesText: String,
        tagsText: String,
        categoriesText: String,
        recipeTypesText: String
    ): RegistrySnapshot {
        val manifest = parseManifest(manifestText)
        val items = json.decodeFromString<List<ItemDto>>(itemsText).map { it.toDomain() }
        val recipes = json.decodeFromString<List<RecipeDto>>(recipesText).map { it.toDomain() }
        val rawTags = json.decodeFromString<Map<TagID, List<ItemID>>>(tagsText)
        val categories = json.decodeFromString<List<CategoryDto>>(categoriesText).map { it.toDomain() }
        val recipeTypes = json.decodeFromString<List<RecipeTypeDto>>(recipeTypesText)
            .map { it.toDomain() }
            .associateBy { it.id }

        val itemRegistry = ItemRegistry().apply {
            items.forEach { addItem(it) }
        }
        val recipeRegistry = RecipeRegistry().apply {
            recipes.forEach { addRecipe(it) }
        }
        val tagRegistry = TagRegistry(rawTags)

        return RegistrySnapshot(
            manifest = manifest,
            source = source,
            itemRegistry = itemRegistry,
            recipeRegistry = recipeRegistry,
            tagRegistry = tagRegistry,
            categories = categories,
            recipeTypes = recipeTypes,
            recipeCount = recipes.size
        )
    }

    companion object {
        fun sha256Hex(bytes: ByteArray): String {
            val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
            return buildString(digest.size * 2) {
                for (b in digest) {
                    append("%02x".format(b.toInt() and 0xFF))
                }
            }
        }
    }
}
