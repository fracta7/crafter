package com.fracta7.crafter

import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.data.local.registry.SUPPORTED_REGISTRY_SCHEMA_VERSION
import com.fracta7.crafter.data.remote.RegistryHttpClient
import com.fracta7.crafter.data.remote.RegistryUpdateManager
import com.fracta7.crafter.data.remote.RegistryUpdateResult
import com.fracta7.crafter.domain.model.RegistryFileChecksum
import com.fracta7.crafter.domain.model.RegistryManifest
import com.fracta7.crafter.domain.model.RegistrySource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class RegistryUpdateManagerTest {

    @get:Rule
    val tempFolder = TemporaryFolder()

    private val json = Json { prettyPrint = true }
    private val loader = RegistryLoader()

    private fun buildBundleFiles(
        registryVersion: Int,
        mcVersion: String = "1.21.11",
        schemaVersion: Int = SUPPORTED_REGISTRY_SCHEMA_VERSION,
        extraItemId: String? = null,
        corruptChecksumForItems: Boolean = false
    ): Map<String, ByteArray> {
        val itemsJson = if (extraItemId == null) {
            """[{"id":"oak_log","name":"Oak Log","stackSize":64,"decomposable":false,"tags":["building"]}]"""
        } else {
            """[{"id":"oak_log","name":"Oak Log","stackSize":64,"decomposable":false,"tags":["building"]},{"id":"$extraItemId","name":"New Item","stackSize":64,"decomposable":false,"tags":["building"]}]"""
        }
        val baseFiles = linkedMapOf(
            "items.json" to itemsJson.toByteArray(Charsets.UTF_8),
            "recipes.json" to "[]".toByteArray(Charsets.UTF_8),
            "item_tags.json" to "{}".toByteArray(Charsets.UTF_8),
            "categories.json" to """[{"id":"building","name":"Building Blocks","item":"oak_log"}]""".toByteArray(Charsets.UTF_8),
            "recipe_types.json" to """[{"id":"crafting_shaped","name":"Crafting","item":"crafting_table"}]""".toByteArray(Charsets.UTF_8)
        )
        val checksums = linkedMapOf<String, RegistryFileChecksum>()
        baseFiles.forEach { (name, bytes) ->
            val sha = if (corruptChecksumForItems && name == "items.json") {
                "0".repeat(64)
            } else {
                RegistryLoader.sha256Hex(bytes)
            }
            checksums[name] = RegistryFileChecksum(sha256 = sha, bytes = bytes.size.toLong())
        }
        val manifest = RegistryManifest(
            schemaVersion = schemaVersion,
            mcVersion = mcVersion,
            registryVersion = registryVersion,
            generatedAt = "2026-11-20T00:00:00Z",
            iconBaseUrl = "https://example.com/items/",
            files = checksums
        )
        baseFiles["manifest.json"] = json.encodeToString(manifest).toByteArray(Charsets.UTF_8)
        return baseFiles
    }

    private fun writeBundled(dir: File): FakeAppRepository {
        val files = buildBundleFiles(registryVersion = 1, mcVersion = "1.21.11")
        files.forEach { (name, bytes) -> File(dir, name).writeBytes(bytes) }
        val snap = loader.loadFromDirectory(dir, RegistrySource.BUNDLED)
        return FakeAppRepository(snap)
    }

    @Test
    fun returnsUpToDateWhenRemoteVersionMatchesActive() = runTest {
        val bundledDir = tempFolder.newFolder("bundled_up_to_date")
        val cacheDir = File(tempFolder.root, "cache_up_to_date")
        val tmpDir = File(tempFolder.root, "tmp_up_to_date")
        val appRepo = writeBundled(bundledDir)
        val prefsRepo = FakeUserPreferencesRepository()

        val remote = buildBundleFiles(registryVersion = 1)
        val manager = RegistryUpdateManager(
            appRepository = appRepo,
            userPreferencesRepository = prefsRepo,
            registryLoader = loader,
            httpClient = RegistryHttpClient { url -> remote.getValue(url.substringAfterLast('/')) },
            loadBundledSnapshot = { loader.loadFromDirectory(bundledDir, RegistrySource.BUNDLED) },
            cacheDir = cacheDir,
            tmpDir = tmpDir,
            ioDispatcher = UnconfinedTestDispatcher(testScheduler)
        )

        val result = manager.checkAndApplyUpdate("https://example.com/registry/manifest.json")
        assertEquals(RegistryUpdateResult.UpToDate, result)
        assertFalse(cacheDir.exists())
    }

    @Test
    fun downloadsVerifiesEmitsUpdateAndResetsCleanly() = runTest {
        val bundledDir = tempFolder.newFolder("bundled_ota")
        val cacheDir = File(tempFolder.root, "cache_ota")
        val tmpDir = File(tempFolder.root, "tmp_ota")
        val appRepo = writeBundled(bundledDir)
        val prefsRepo = FakeUserPreferencesRepository()

        val remote = buildBundleFiles(
            registryVersion = 2,
            mcVersion = "1.21.12",
            extraItemId = "copper_golem_statue"
        )
        val manager = RegistryUpdateManager(
            appRepository = appRepo,
            userPreferencesRepository = prefsRepo,
            registryLoader = loader,
            httpClient = RegistryHttpClient { url -> remote.getValue(url.substringAfterLast('/')) },
            loadBundledSnapshot = { loader.loadFromDirectory(bundledDir, RegistrySource.BUNDLED) },
            cacheDir = cacheDir,
            tmpDir = tmpDir,
            ioDispatcher = UnconfinedTestDispatcher(testScheduler)
        )

        val result = manager.checkAndApplyUpdate("https://example.com/registry/manifest.json")
        assertTrue(result is RegistryUpdateResult.Updated)
        assertFalse(tmpDir.exists())
        assertTrue(cacheDir.exists())
        assertEquals(RegistrySource.OTA_CACHE, appRepo.registryState.value.source)
        assertEquals("1.21.12", appRepo.registryState.value.manifest.mcVersion)
        assertNotNull(appRepo.itemRegistryProvider().getItem("copper_golem_statue"))

        manager.resetToBundled()
        assertFalse(cacheDir.exists())
        assertEquals(RegistrySource.BUNDLED, appRepo.registryState.value.source)
        assertEquals(null, appRepo.itemRegistryProvider().getItem("copper_golem_statue"))
    }

    @Test
    fun rejectsChecksumMismatchAndIncompatibleSchemaWithoutCorruptingState() = runTest {
        val bundledDir = tempFolder.newFolder("bundled_fail")
        val cacheDir = File(tempFolder.root, "cache_fail")
        val tmpDir = File(tempFolder.root, "tmp_fail")
        val appRepo = writeBundled(bundledDir)
        val prefsRepo = FakeUserPreferencesRepository()

        val badChecksumRemote = buildBundleFiles(
            registryVersion = 2,
            corruptChecksumForItems = true
        )
        val managerBadSha = RegistryUpdateManager(
            appRepository = appRepo,
            userPreferencesRepository = prefsRepo,
            registryLoader = loader,
            httpClient = RegistryHttpClient { url ->
                badChecksumRemote.getValue(url.substringAfterLast('/'))
            },
            loadBundledSnapshot = { loader.loadFromDirectory(bundledDir, RegistrySource.BUNDLED) },
            cacheDir = cacheDir,
            tmpDir = tmpDir,
            ioDispatcher = UnconfinedTestDispatcher(testScheduler)
        )

        val shaResult = managerBadSha.checkAndApplyUpdate("https://example.com/registry/manifest.json")
        assertTrue(shaResult is RegistryUpdateResult.Error)
        assertFalse(tmpDir.exists())
        assertFalse(cacheDir.exists())
        assertEquals(1, appRepo.registryState.value.manifest.registryVersion)

        val badSchemaRemote = buildBundleFiles(registryVersion = 5, schemaVersion = 99)
        val managerBadSchema = RegistryUpdateManager(
            appRepository = appRepo,
            userPreferencesRepository = prefsRepo,
            registryLoader = loader,
            httpClient = RegistryHttpClient { url ->
                badSchemaRemote.getValue(url.substringAfterLast('/'))
            },
            loadBundledSnapshot = { loader.loadFromDirectory(bundledDir, RegistrySource.BUNDLED) },
            cacheDir = cacheDir,
            tmpDir = tmpDir,
            ioDispatcher = UnconfinedTestDispatcher(testScheduler)
        )

        val schemaResult = managerBadSchema.checkAndApplyUpdate("https://example.com/registry/manifest.json")
        assertEquals(RegistryUpdateResult.IncompatibleSchema(99), schemaResult)
        assertEquals(1, appRepo.registryState.value.manifest.registryVersion)
    }
}
