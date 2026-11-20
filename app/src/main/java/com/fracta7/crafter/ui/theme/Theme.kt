package com.fracta7.crafter.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

@Immutable
data class RegistryIconConfig(
    val bundledItemFiles: Set<String> = emptySet(),
    val iconBaseUrl: String = ""
)

val LocalShowShulkerUnits = compositionLocalOf { true }
val LocalRegistryIconConfig = compositionLocalOf { RegistryIconConfig() }

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldDark,
    onPrimary = OnEmeraldDark,
    primaryContainer = EmeraldContainerDark,
    onPrimaryContainer = OnEmeraldContainerDark,
    secondary = CopperDark,
    onSecondary = OnCopperDark,
    secondaryContainer = CopperContainerDark,
    onSecondaryContainer = OnCopperContainerDark,
    tertiary = LapisDark,
    onTertiary = OnLapisDark,
    tertiaryContainer = LapisContainerDark,
    onTertiaryContainer = OnLapisContainerDark,
    background = DeepslateBackgroundDark,
    surface = DeepslateSurfaceDark,
    surfaceVariant = DeepslateSurfaceVariantDark
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldLight,
    onPrimary = OnEmeraldLight,
    primaryContainer = EmeraldContainerLight,
    onPrimaryContainer = OnEmeraldContainerLight,
    secondary = CopperLight,
    onSecondary = OnCopperLight,
    secondaryContainer = CopperContainerLight,
    onSecondaryContainer = OnCopperContainerLight,
    tertiary = LapisLight,
    onTertiary = OnLapisLight,
    tertiaryContainer = LapisContainerLight,
    onTertiaryContainer = OnLapisContainerLight,
    background = StoneBackgroundLight,
    surface = StoneSurfaceLight,
    surfaceVariant = StoneSurfaceVariantLight
)

@Composable
fun CrafterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    useMojanglesFont: Boolean = true,
    showShulkerUnits: Boolean = true,
    registryIconConfig: RegistryIconConfig = RegistryIconConfig(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalShowShulkerUnits provides showShulkerUnits,
        LocalRegistryIconConfig provides registryIconConfig
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = if (useMojanglesFont) AppTypography else Typography(),
            content = content
        )
    }
}
