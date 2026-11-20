package com.fracta7.crafter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.fracta7.crafter.R

val AppFontFamily = FontFamily(
    Font(R.font.mojangles, FontWeight.Normal),
    Font(R.font.mojangles, FontWeight.Medium),
    Font(R.font.mojangles, FontWeight.Bold)
)

private val DefaultTypography = Typography()

val AppTypography = Typography(
    displayLarge = DefaultTypography.displayLarge.copy(fontFamily = AppFontFamily),
    displayMedium = DefaultTypography.displayMedium.copy(fontFamily = AppFontFamily),
    displaySmall = DefaultTypography.displaySmall.copy(fontFamily = AppFontFamily),
    headlineLarge = DefaultTypography.headlineLarge.copy(fontFamily = AppFontFamily),
    headlineMedium = DefaultTypography.headlineMedium.copy(fontFamily = AppFontFamily),
    headlineSmall = DefaultTypography.headlineSmall.copy(fontFamily = AppFontFamily),
    titleLarge = DefaultTypography.titleLarge.copy(fontFamily = AppFontFamily),
    titleMedium = DefaultTypography.titleMedium.copy(fontFamily = AppFontFamily),
    titleSmall = DefaultTypography.titleSmall.copy(fontFamily = AppFontFamily),
    bodyLarge = DefaultTypography.bodyLarge.copy(fontFamily = AppFontFamily),
    bodyMedium = DefaultTypography.bodyMedium.copy(fontFamily = AppFontFamily),
    bodySmall = DefaultTypography.bodySmall.copy(fontFamily = AppFontFamily),
    labelLarge = DefaultTypography.labelLarge.copy(fontFamily = AppFontFamily),
    labelMedium = DefaultTypography.labelMedium.copy(fontFamily = AppFontFamily),
    labelSmall = DefaultTypography.labelSmall.copy(fontFamily = AppFontFamily)
)
