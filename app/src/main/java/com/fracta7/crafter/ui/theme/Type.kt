package com.fracta7.crafter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.fracta7.crafter.R

val AppFontFamily = FontFamily(
    Font(R.font.mojangles, FontWeight.Normal),
    Font(R.font.mojangles, FontWeight.Medium),
    Font(R.font.mojangles, FontWeight.Bold)
)


val AppTypography = Typography(
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = AppFontFamily
    ),
    bodyMedium = Typography().bodyMedium.copy(
        fontFamily = AppFontFamily
    ),
    bodySmall = Typography().bodySmall.copy(
        fontFamily = AppFontFamily
    ),
    titleLarge = Typography().titleLarge.copy(
        fontFamily = AppFontFamily
    ),
    titleMedium = Typography().titleMedium.copy(
        fontFamily = AppFontFamily
    ),
    titleSmall = Typography().titleSmall.copy(
        fontFamily = AppFontFamily
    ),
    labelLarge = Typography().labelLarge.copy(
        fontFamily = AppFontFamily
    )
)