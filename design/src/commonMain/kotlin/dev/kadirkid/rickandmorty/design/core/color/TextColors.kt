package dev.kadirkid.rickandmorty.design.core.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.kadirkid.rickandmorty.design.core.TextColor
import dev.kadirkid.rickandmorty.design.md_theme_dark_inversePrimary
import dev.kadirkid.rickandmorty.design.md_theme_dark_primary
import dev.kadirkid.rickandmorty.design.md_theme_dark_secondary
import dev.kadirkid.rickandmorty.design.md_theme_dark_tertiary
import dev.kadirkid.rickandmorty.design.md_theme_light_inversePrimary
import dev.kadirkid.rickandmorty.design.md_theme_light_primary
import dev.kadirkid.rickandmorty.design.md_theme_light_secondary
import dev.kadirkid.rickandmorty.design.md_theme_light_tertiary

@Immutable
class TextColors internal constructor(
    val primary: TextColor,
    val secondary: TextColor,
    val tertiary: TextColor,
    val primaryInverse: TextColor
)

val LocalTextColors: ProvidableCompositionLocal<TextColors> =
    staticCompositionLocalOf { error("No TextColors provided") }

internal fun lightTextColors(): TextColors = TextColors(
    primary = TextColor(md_theme_light_primary),
    secondary = TextColor(md_theme_light_secondary),
    tertiary = TextColor(md_theme_light_tertiary),
    primaryInverse = TextColor(md_theme_light_inversePrimary)
)

internal fun darkTextColors(): TextColors = TextColors(
    primary = TextColor(md_theme_dark_primary),
    secondary = TextColor(md_theme_dark_secondary),
    tertiary = TextColor(md_theme_dark_tertiary),
    primaryInverse = TextColor(md_theme_dark_inversePrimary)
)

fun TextColors.withNames() = buildList {
    add(Pair("primary", primary))
    add(Pair("secondary", secondary))
    add(Pair("tertiary", tertiary))
    add(Pair("primaryInverse", primaryInverse))
}