package com.example.moviesearchapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
    // Default Material 3 typography values
    val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = FontFamily.Default),
    displayMedium = baseline.displayMedium.copy(fontFamily = FontFamily.Default),
    displaySmall = baseline.displaySmall.copy(fontFamily = FontFamily.Default),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = FontFamily.Default),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = FontFamily.Default),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = FontFamily.Default),
    titleLarge = baseline.titleLarge.copy(fontFamily = FontFamily.Default),
    titleMedium = baseline.titleMedium.copy(fontFamily = FontFamily.Default),
    titleSmall = baseline.titleSmall.copy(fontFamily = FontFamily.Default),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = FontFamily.Default),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = FontFamily.Default),
    bodySmall = baseline.bodySmall.copy(fontFamily = FontFamily.Default),
    labelLarge = baseline.labelLarge.copy(fontFamily = FontFamily.Default),
    labelMedium = baseline.labelMedium.copy(fontFamily = FontFamily.Default),
    labelSmall = baseline.labelSmall.copy(fontFamily = FontFamily.Default),
)

