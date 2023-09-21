package dev.kadirkid.rickandmorty.design.core.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import dev.kadirkid.rickandmorty.design.md_theme_dark_background
import dev.kadirkid.rickandmorty.design.md_theme_dark_error
import dev.kadirkid.rickandmorty.design.md_theme_dark_errorContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_inverseOnSurface
import dev.kadirkid.rickandmorty.design.md_theme_dark_inversePrimary
import dev.kadirkid.rickandmorty.design.md_theme_dark_inverseSurface
import dev.kadirkid.rickandmorty.design.md_theme_dark_onBackground
import dev.kadirkid.rickandmorty.design.md_theme_dark_onError
import dev.kadirkid.rickandmorty.design.md_theme_dark_onErrorContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onPrimary
import dev.kadirkid.rickandmorty.design.md_theme_dark_onPrimaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onSecondary
import dev.kadirkid.rickandmorty.design.md_theme_dark_onSecondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onSurface
import dev.kadirkid.rickandmorty.design.md_theme_dark_onSurfaceVariant
import dev.kadirkid.rickandmorty.design.md_theme_dark_onTertiary
import dev.kadirkid.rickandmorty.design.md_theme_dark_onTertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_outline
import dev.kadirkid.rickandmorty.design.md_theme_dark_outlineVariant
import dev.kadirkid.rickandmorty.design.md_theme_dark_primary
import dev.kadirkid.rickandmorty.design.md_theme_dark_primaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_scrim
import dev.kadirkid.rickandmorty.design.md_theme_dark_secondary
import dev.kadirkid.rickandmorty.design.md_theme_dark_secondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_surface
import dev.kadirkid.rickandmorty.design.md_theme_dark_surfaceTint
import dev.kadirkid.rickandmorty.design.md_theme_dark_surfaceVariant
import dev.kadirkid.rickandmorty.design.md_theme_dark_tertiary
import dev.kadirkid.rickandmorty.design.md_theme_dark_tertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_background
import dev.kadirkid.rickandmorty.design.md_theme_light_error
import dev.kadirkid.rickandmorty.design.md_theme_light_errorContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_inverseOnSurface
import dev.kadirkid.rickandmorty.design.md_theme_light_inversePrimary
import dev.kadirkid.rickandmorty.design.md_theme_light_inverseSurface
import dev.kadirkid.rickandmorty.design.md_theme_light_onBackground
import dev.kadirkid.rickandmorty.design.md_theme_light_onError
import dev.kadirkid.rickandmorty.design.md_theme_light_onErrorContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onPrimary
import dev.kadirkid.rickandmorty.design.md_theme_light_onPrimaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onSecondary
import dev.kadirkid.rickandmorty.design.md_theme_light_onSecondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onSurface
import dev.kadirkid.rickandmorty.design.md_theme_light_onSurfaceVariant
import dev.kadirkid.rickandmorty.design.md_theme_light_onTertiary
import dev.kadirkid.rickandmorty.design.md_theme_light_onTertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_outline
import dev.kadirkid.rickandmorty.design.md_theme_light_outlineVariant
import dev.kadirkid.rickandmorty.design.md_theme_light_primary
import dev.kadirkid.rickandmorty.design.md_theme_light_primaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_scrim
import dev.kadirkid.rickandmorty.design.md_theme_light_secondary
import dev.kadirkid.rickandmorty.design.md_theme_light_secondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_surface
import dev.kadirkid.rickandmorty.design.md_theme_light_surfaceTint
import dev.kadirkid.rickandmorty.design.md_theme_light_surfaceVariant
import dev.kadirkid.rickandmorty.design.md_theme_light_tertiary
import dev.kadirkid.rickandmorty.design.md_theme_light_tertiaryContainer


internal fun materialColors(isDarkTheme: Boolean): ColorScheme {
    return if (isDarkTheme) {
        DarkColors
    } else {
        LightColors
    }
}

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)