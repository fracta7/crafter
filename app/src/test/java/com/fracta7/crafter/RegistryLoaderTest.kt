package com.fracta7.crafter

import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.data.local.registry.SUPPORTED_REGISTRY_SCHEMA_VERSION
import com.fracta7.crafter.domain.model.RegistryFileChecksum
import com.fracta7.crafter.domain.model.RegistryManifest
import com.fracta7.crafter.domain.model.RegistrySource
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class RegistryLoaderTest {

    @get:Rule
    val tempFolder = TemporaryFolder()

    private val json = Json { prettyPrint = true }

    private fun writeRegistryFiles(
        dir: File,
        registryVersion: Int,
        schemaVersion: Int = SUPPORTED_REGISTRY_SCHEMA_VERSION,
        mcVersion: String = "1.21.11",
        tamperItemsAfterChecksum: Boolean = false
    ) {
        val payloads = linkedMapOf(
            "items.json" to """[{"id":"stick","name":"Stick","stackSize":64,"decomposable":false,"tags":["ingredients"]}]""",
            "recipes.json" to """[]""",
            "item_tags.json" to """{}""",
            "categories.json" to """[{"id":"ingredients","name":"Ingredients","item":"stick"}]""",
            "recipe_types.json" to """[{"id":"crafting_shaped","name":"Crafting","item":"crafting_table"}]"""
        )
        val checksums = linkedMapOf<String, RegistryFileChecksum>()
        payloads.forEach { (name, content) ->
            val bytes = content.toByteArray(Charsets.UTF_8)
            File(dir, name).writeBytes(bytes)
            checksums[name] = RegistryFileChecksum(
                sha256 = RegistryLoader.sha256Hex(bytes),
                bytes = bytes.size.toLong()
            )
        }
        val manifest = RegistryManifest(
            schemaVersion = schemaVersion,
            mcVersion = mcVersion,
            registryVersion = registryVersion,
            generatedAt = "2026-11-20T00:00:00Z",
            files = checksums
        )
        File(dir, "manifest.json").writeText(json.encodeToString(manifest))
        if (tamperItemsAfterChecksum) {
            File(dir, "items.json").writeText("""[{"id":"corrupt","name":"Bad","stackSize":64,"decomposable":false,"tags":["other"]}]""")
        }
    }

    @Test
    fun loadsValidDirectorySnapshotAndVerifiesChecksums() {
        val dir = tempFolder.newFolder("valid_reg")
        writeRegistryFiles(dir, registryVersion = 2, mcVersion = "1.21.12")

        val loader = RegistryLoader()
        val manifest = loader.parseManifest(File(dir, "manifest.json").readText())
        assertTrue(loader.verifyDirectoryChecksums(dir, manifest))

        val snapshot = loader.loadFromDirectory(dir, RegistrySource.OTA_CACHE)
        assertEquals(2, snapshot.manifest.registryVersion)
        assertEquals("1.21.12", snapshot.manifest.mcVersion)
        assertEquals(RegistrySource.OTA_CACHE, snapshot.source)
        assertEquals("Stick", snapshot.itemRegistry.getItem("stick")?.name)
    }

    @Test
    fun rejectsTamperedCacheViaChecksumCheck() {
        val dir = tempFolder.newFolder("tampered_reg")
        writeRegistryFiles(dir, registryVersion = 3, tamperItemsAfterChecksum = true)

        val loader = RegistryLoader()
        val manifest = loader.parseManifest(File(dir, "manifest.json").readText())
        assertFalse(loader.verifyDirectoryChecksums(dir, manifest))
    }
}
