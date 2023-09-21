/**
 * Copyright 2023 Abdulahi Osoble
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class BackgroundColors internal constructor(
    public val primary: BackgroundColor,
    public val secondary: BackgroundColor,
    public val tertiary: BackgroundColor,
    public val onPrimary: BackgroundColor,
    public val onSecondary: BackgroundColor,
    public val onTertiary: BackgroundColor,
    public val error: BackgroundColor,
)

public val LocalBackgroundColors: ProvidableCompositionLocal<BackgroundColors> =
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

internal fun BackgroundColors.withNames() = buildList {
    add("primary" to primary)
    add("secondary" to secondary)
    add("tertiary" to tertiary)
    add("error" to error)
}
