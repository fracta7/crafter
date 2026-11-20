package com.fracta7.crafter.ui.settings_screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fracta7.crafter.data.remote.RegistryUpdateManager
import com.fracta7.crafter.data.remote.RegistryUpdateResult
import com.fracta7.crafter.domain.model.PreferredFuel
import com.fracta7.crafter.domain.model.PreferredStone
import com.fracta7.crafter.domain.model.PreferredWood
import com.fracta7.crafter.domain.model.RegistrySource
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class RegistryInfoUiState(
    val mcVersion: String = "26.3",
    val registryVersion: Int = 1,
    val itemCount: Int = 0,
    val recipeCount: Int = 0,
    val source: RegistrySource = RegistrySource.BUNDLED
)

@Immutable
data class RegistryUpdateUiState(
    val isChecking: Boolean = false,
    val statusMessage: String? = null,
    val isError: Boolean = false
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val appRepository: AppRepository,
    private val registryUpdateManager: RegistryUpdateManager
) : ViewModel() {

    val preferences: StateFlow<UserPreferences> = userPreferencesRepository.preferences
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserPreferences()
        )

    val registryInfo: StateFlow<RegistryInfoUiState> = appRepository.registryState
        .map { snapshot ->
            RegistryInfoUiState(
                mcVersion = snapshot.manifest.mcVersion,
                registryVersion = snapshot.manifest.registryVersion,
                itemCount = snapshot.itemRegistry.getAll().size,
                recipeCount = snapshot.recipeCount,
                source = snapshot.source
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RegistryInfoUiState(
                mcVersion = appRepository.registryState.value.manifest.mcVersion,
                registryVersion = appRepository.registryState.value.manifest.registryVersion,
                itemCount = appRepository.registryState.value.itemRegistry.getAll().size,
                recipeCount = appRepository.registryState.value.recipeCount,
                source = appRepository.registryState.value.source
            )
        )

    private val _updateState = MutableStateFlow(RegistryUpdateUiState())
    val updateState: StateFlow<RegistryUpdateUiState> = _updateState.asStateFlow()

    fun setPreferredFuel(fuel: PreferredFuel) {
        viewModelScope.launch { userPreferencesRepository.setPreferredFuel(fuel) }
    }

    fun setPreferredWood(wood: PreferredWood) {
        viewModelScope.launch { userPreferencesRepository.setPreferredWood(wood) }
    }

    fun setPreferredStone(stone: PreferredStone) {
        viewModelScope.launch { userPreferencesRepository.setPreferredStone(stone) }
    }

    fun setPreferStonecutter(prefer: Boolean) {
        viewModelScope.launch { userPreferencesRepository.setPreferStonecutter(prefer) }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch { userPreferencesRepository.setThemeMode(mode) }
    }

    fun setDynamicColor(enabled: Boolean) {
        viewModelScope.launch { userPreferencesRepository.setDynamicColor(enabled) }
    }

    fun setUseMojanglesFont(enabled: Boolean) {
        viewModelScope.launch { userPreferencesRepository.setUseMojanglesFont(enabled) }
    }

    fun setShowShulkerUnits(enabled: Boolean) {
        viewModelScope.launch { userPreferencesRepository.setShowShulkerUnits(enabled) }
    }

    fun setKeepScreenOnWhileCrafting(enabled: Boolean) {
        viewModelScope.launch { userPreferencesRepository.setKeepScreenOnWhileCrafting(enabled) }
    }

    fun checkForRegistryUpdates() {
        if (_updateState.value.isChecking) return
        _updateState.value = RegistryUpdateUiState(isChecking = true, statusMessage = "Checking GitHub manifest...")
        viewModelScope.launch {
            _updateState.value = when (val result = registryUpdateManager.checkAndApplyUpdate()) {
                is RegistryUpdateResult.Updated -> RegistryUpdateUiState(
                    statusMessage = "Updated to Minecraft ${result.manifest.mcVersion} (v${result.manifest.registryVersion})."
                )
                RegistryUpdateResult.UpToDate -> RegistryUpdateUiState(
                    statusMessage = "Already on the latest registry version."
                )
                is RegistryUpdateResult.IncompatibleSchema -> RegistryUpdateUiState(
                    statusMessage = "Update requires newer app version (schema v${result.remoteSchemaVersion}).",
                    isError = true
                )
                is RegistryUpdateResult.Error -> RegistryUpdateUiState(
                    statusMessage = result.message,
                    isError = true
                )
            }
        }
    }

    fun resetToBundledRegistry() {
        if (_updateState.value.isChecking) return
        viewModelScope.launch {
            _updateState.value = when (val result = registryUpdateManager.resetToBundled()) {
                is RegistryUpdateResult.Error -> RegistryUpdateUiState(
                    statusMessage = result.message,
                    isError = true
                )
                else -> RegistryUpdateUiState(
                    statusMessage = "Restored bundled offline registry."
                )
            }
        }
    }
}
