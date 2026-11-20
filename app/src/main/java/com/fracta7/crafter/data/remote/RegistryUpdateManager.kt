package com.fracta7.crafter.data.remote

import com.fracta7.crafter.data.local.registry.RegistryLoader
import com.fracta7.crafter.data.local.registry.SUPPORTED_REGISTRY_SCHEMA_VERSION
import com.fracta7.crafter.domain.model.RegistryManifest
import com.fracta7.crafter.domain.model.RegistrySnapshot
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

const val DEFAULT_MANIFEST_URL =
    "https://raw.githubusercontent.com/fracta7/crafter/master/app/src/main/assets/registry/manifest.json"

fun interface RegistryHttpClient {
    suspend fun getBytes(url: String): ByteArray
}

class HttpUrlConnectionRegistryClient(
    private val connectTimeoutMs: Int = 10_000,
    private val readTimeoutMs: Int = 15_000,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RegistryHttpClient {
    override suspend fun getBytes(url: String): ByteArray = withContext(ioDispatcher) {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = connectTimeoutMs
            readTimeout = readTimeoutMs
            setRequestProperty("User-Agent", "CrafterAndroid/1.0")
        }
        try {
            val code = connection.responseCode
            if (code !in 200..299) {
                throw IllegalStateException("HTTP $code when fetching $url")
            }
            connection.inputStream.use { it.readBytes() }
        } finally {
            connection.disconnect()
        }
    }
}

sealed interface RegistryUpdateResult {
    data object UpToDate : RegistryUpdateResult
    data class Updated(val manifest: RegistryManifest) : RegistryUpdateResult
    data class IncompatibleSchema(val remoteSchemaVersion: Int) : RegistryUpdateResult
    data class Error(val message: String) : RegistryUpdateResult
}

class RegistryUpdateManager(
    private val appRepository: AppRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val registryLoader: RegistryLoader,
    private val httpClient: RegistryHttpClient,
    private val loadBundledSnapshot: () -> RegistrySnapshot,
    private val cacheDir: File,
    private val tmpDir: File,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun checkAndApplyUpdate(
        manifestUrl: String = DEFAULT_MANIFEST_URL
    ): RegistryUpdateResult = withContext(ioDispatcher) {
        try {
            val manifestBytes = httpClient.getBytes(manifestUrl)
            val remoteManifest = registryLoader.parseManifest(manifestBytes.toString(Charsets.UTF_8))

            userPreferencesRepository.setLastRegistryCheckMillis(System.currentTimeMillis())

            if (remoteManifest.schemaVersion != SUPPORTED_REGISTRY_SCHEMA_VERSION) {
                return@withContext RegistryUpdateResult.IncompatibleSchema(remoteManifest.schemaVersion)
            }

            val activeVersion = appRepository.registryState.value.manifest.registryVersion
            if (remoteManifest.registryVersion <= activeVersion) {
                return@withContext RegistryUpdateResult.UpToDate
            }

            if (remoteManifest.files.isEmpty()) {
                return@withContext RegistryUpdateResult.Error("Remote manifest lists no registry files.")
            }

            if (tmpDir.exists()) tmpDir.deleteRecursively()
            tmpDir.mkdirs()

            val baseUrl = manifestUrl.substringBeforeLast('/') + "/"
            File(tmpDir, "manifest.json").writeBytes(manifestBytes)

            for ((fileName, expected) in remoteManifest.files) {
                val safeName = File(fileName).name
                if (safeName != fileName || safeName.isBlank()) {
                    return@withContext RegistryUpdateResult.Error("Invalid filename in manifest: $fileName")
                }
                val fileBytes = httpClient.getBytes(baseUrl + safeName)
                val actualSha = RegistryLoader.sha256Hex(fileBytes)
                if (
                    fileBytes.size.toLong() != expected.bytes ||
                    !actualSha.equals(expected.sha256, ignoreCase = true)
                ) {
                    return@withContext RegistryUpdateResult.Error("Checksum mismatch for $safeName")
                }
                File(tmpDir, safeName).writeBytes(fileBytes)
            }

            val validatedSnapshot = registryLoader.loadFromDirectory(
                directory = tmpDir,
                source = RegistrySource.OTA_CACHE
            )

            promoteTmpToCache()
            appRepository.updateSnapshot(validatedSnapshot)
            RegistryUpdateResult.Updated(remoteManifest)
        } catch (t: Throwable) {
            RegistryUpdateResult.Error(t.message ?: "Update failed")
        } finally {
            if (tmpDir.exists()) tmpDir.deleteRecursively()
        }
    }

    suspend fun resetToBundled(): RegistryUpdateResult = withContext(ioDispatcher) {
        try {
            if (cacheDir.exists()) cacheDir.deleteRecursively()
            val bundled = loadBundledSnapshot()
            appRepository.updateSnapshot(bundled)
            RegistryUpdateResult.UpToDate
        } catch (t: Throwable) {
            RegistryUpdateResult.Error(t.message ?: "Failed to reload bundled data")
        }
    }

    private fun promoteTmpToCache() {
        cacheDir.parentFile?.mkdirs()
        val backupDir = File(cacheDir.parentFile, "${cacheDir.name}_bak")
        if (backupDir.exists()) backupDir.deleteRecursively()
        val hadPreviousCache = cacheDir.exists()
        if (hadPreviousCache && !cacheDir.renameTo(backupDir)) {
            cacheDir.deleteRecursively()
        }
        if (!tmpDir.renameTo(cacheDir)) {
            tmpDir.copyRecursively(cacheDir, overwrite = true)
        }
        if (backupDir.exists()) backupDir.deleteRecursively()
    }
}
