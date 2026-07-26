package com.example.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = NoxAccent,
    background = NoxBackground,
    surface = NoxSurface,
    surfaceVariant = NoxCard,
    onPrimary = NoxBackground,
    onBackground = NoxTextPrimary,
    onSurface = NoxTextPrimary,
    onSurfaceVariant = NoxTextSecondary,
    outlineVariant = NoxDivider
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
