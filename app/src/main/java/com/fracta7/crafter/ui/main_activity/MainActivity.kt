package com.fracta7.crafter.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.fracta7.crafter.domain.model.ThemeMode
import com.fracta7.crafter.domain.model.UserPreferences
import com.fracta7.crafter.domain.repository.AppRepository
import com.fracta7.crafter.domain.repository.UserPreferencesRepository
import com.fracta7.crafter.ui.navigation.Navigation
import com.fracta7.crafter.ui.theme.CrafterTheme
import com.fracta7.crafter.ui.theme.RegistryIconConfig
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository

    @Inject
    lateinit var appRepository: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val prefs by userPreferencesRepository.preferences
                .collectAsState(initial = UserPreferences())
            val registrySnapshot by appRepository.registryState.collectAsState()
            val bundledItemFiles = remember {
                assets.list("items")?.toSet().orEmpty()
            }
            val iconConfig = remember(bundledItemFiles, registrySnapshot.manifest.iconBaseUrl) {
                RegistryIconConfig(
                    bundledItemFiles = bundledItemFiles,
                    iconBaseUrl = registrySnapshot.manifest.iconBaseUrl
                )
            }
            val darkTheme = when (prefs.themeMode) {
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
            }
            CrafterTheme(
                darkTheme = darkTheme,
                dynamicColor = prefs.dynamicColor,
                useMojanglesFont = prefs.useMojanglesFont,
                showShulkerUnits = prefs.showShulkerUnits,
                registryIconConfig = iconConfig
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}
