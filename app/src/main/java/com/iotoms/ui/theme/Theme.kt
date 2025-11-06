package com.iotoms.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryTealLight,
    onPrimary = Color(0xFF202626),
    primaryContainer = Color(0xFF005046),
    onPrimaryContainer = Color(0xFFA7F3E8),

    secondary = AccentCoralLight,
    onSecondary = Color(0xFF561E15),
    secondaryContainer = Color(0xFF7D3528),
    onSecondaryContainer = Color(0xFFFFDAD4),

    tertiary = AccentPurpleLight,
    onTertiary = Color(0xFF3B0074),
    tertiaryContainer = Color(0xFF520088),
    onTertiaryContainer = Color(0xFFF2DAFF),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = Color(0xFF202626),
    onBackground = Color(0xFFE2E2E6),

    surface = Color(0xFF1A1C1E),
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF42474E),
    onSurfaceVariant = Color(0xFFC2C7CF),

    outline = NeutralGray600,
    outlineVariant = NeutralGray700,

    scrim = Color.Black.copy(alpha = 0.5f),

    inverseSurface = Color(0xFFE2E2E6),
    inverseOnSurface = Color(0xFF2E3135),
    inversePrimary = PrimaryTeal,

    surfaceTint = PrimaryTealLight
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryTeal,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF002019),

    secondary = AccentCoral,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFCCBC),
    onSecondaryContainer = Color(0xFF3E0400),

    tertiary = AccentPurple,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE1BEE7),
    onTertiaryContainer = Color(0xFF21005D),

    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    background = Color(0xFFFDFDFD),
    onBackground = NeutralGray900,

    surface = Color.White,
    onSurface = NeutralGray900,
    surfaceVariant = NeutralGray100,
    onSurfaceVariant = NeutralGray700,

    outline = NeutralGray400,
    outlineVariant = NeutralGray300,

    scrim = Color.Black.copy(alpha = 0.32f),

    inverseSurface = NeutralGray800,
    inverseOnSurface = NeutralGray50,
    inversePrimary = PrimaryTealLight,

    surfaceTint = PrimaryTeal
)

@Composable
fun OnePosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}