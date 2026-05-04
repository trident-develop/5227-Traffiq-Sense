package com.eyecon.glo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = SurfaceWhite,
    primaryContainer = SoftBlue,
    onPrimaryContainer = DeepBlue,
    secondary = AccentBlue,
    onSecondary = SurfaceWhite,
    secondaryContainer = SoftBlue,
    onSecondaryContainer = DeepBlue,
    tertiary = SkyBlue,
    onTertiary = SurfaceWhite,
    background = PaleBlue,
    onBackground = InkBlue,
    surface = SurfaceWhite,
    onSurface = InkBlue,
    surfaceVariant = SoftBlue,
    onSurfaceVariant = MutedBlue,
    outline = DividerBlue,
    error = DangerRed,
    onError = SurfaceWhite,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content,
    )
}
