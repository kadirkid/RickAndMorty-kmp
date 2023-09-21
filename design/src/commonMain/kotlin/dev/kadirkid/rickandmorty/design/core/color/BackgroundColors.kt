package dev.kadirkid.rickandmorty.design.core.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.kadirkid.rickandmorty.design.core.BackgroundColor
import dev.kadirkid.rickandmorty.design.md_theme_dark_errorContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onPrimaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onSecondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_onTertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_primaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_secondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_dark_tertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_errorContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onPrimaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onSecondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_onTertiaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_primaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_secondaryContainer
import dev.kadirkid.rickandmorty.design.md_theme_light_tertiaryContainer

@Immutable
class BackgroundColors internal constructor(
    val primary: BackgroundColor,
    val secondary: BackgroundColor,
    val tertiary: BackgroundColor,
    val onPrimary: BackgroundColor,
    val onSecondary: BackgroundColor,
    val onTertiary: BackgroundColor,
    val error: BackgroundColor
)

val LocalBackgroundColors: ProvidableCompositionLocal<BackgroundColors> =
    staticCompositionLocalOf { error("No BackgroundColors provided") }

internal fun lightBackgroundColors(): BackgroundColors = BackgroundColors(
    primary = BackgroundColor(md_theme_light_primaryContainer),
    secondary = BackgroundColor(md_theme_light_secondaryContainer),
    tertiary = BackgroundColor(md_theme_light_tertiaryContainer),
    onPrimary = BackgroundColor(md_theme_light_onPrimaryContainer),
    onSecondary = BackgroundColor(md_theme_light_onSecondaryContainer),
    onTertiary = BackgroundColor(md_theme_light_onTertiaryContainer),
    error = BackgroundColor(md_theme_light_errorContainer),
)

internal fun darkBackgroundColors(): BackgroundColors = BackgroundColors(
    primary = BackgroundColor(md_theme_dark_primaryContainer),
    secondary = BackgroundColor(md_theme_dark_secondaryContainer),
    tertiary = BackgroundColor(md_theme_dark_tertiaryContainer),
    onPrimary = BackgroundColor(md_theme_dark_onPrimaryContainer),
    onSecondary = BackgroundColor(md_theme_dark_onSecondaryContainer),
    onTertiary = BackgroundColor(md_theme_dark_onTertiaryContainer),
    error = BackgroundColor(md_theme_dark_errorContainer),
)

fun BackgroundColors.withNames() = buildList {
    add("primary" to primary)
    add("secondary" to secondary)
    add("tertiary" to tertiary)
    add("error" to error)
}