package com.example.healthhub.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val HealthHubLightColorScheme = lightColorScheme(
    primary = Color(0xFF33AAFF),
    onPrimary = Color(0xFFF0F0F0),

    background = Color(0xFFE8E8E8),
    onBackground = Color(0xFF616161),

    surface = Color(0xFFF0F0F0),
    onSurface = Color(0xFF101010),

    surfaceVariant = Color(0xFFE1E4ED),
    onSurfaceVariant = Color(0xFF474A52),

    surfaceTint = Color(0xFFF0F0F0),

    outline = Color(0xFF6C6C6C),
    outlineVariant = Color(0xFFC3C3C3),
)

val HealthHubDarkColorScheme = darkColorScheme(
    primary = Color(0xFF44BFFF),

    background = Color(0xFF171717),
    onBackground = Color(0xFF9E9E9E),

    surface = Color(0xFF0F0F0F),
    onSurface = Color(0xFFF0F0F0),

    surfaceVariant = Color(0xFF474A52),
    onSurfaceVariant = Color(0xFFE1E4ED),

    surfaceTint = Color(0xFF0F0F0F),

    outline = Color(0xFF6C6C6C),
    outlineVariant = Color(0xFF3B3B3B),
)
