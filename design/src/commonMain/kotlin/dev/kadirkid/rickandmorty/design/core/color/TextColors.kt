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
public class TextColors internal constructor(
    public val primary: TextColor,
    public val secondary: TextColor,
    public val tertiary: TextColor,
    public val primaryInverse: TextColor,
)

public val LocalTextColors: ProvidableCompositionLocal<TextColors> =
    staticCompositionLocalOf { error("No TextColors provided") }

internal fun lightTextColors(): TextColors = TextColors(
    primary = TextColor(md_theme_light_primary),
    secondary = TextColor(md_theme_light_secondary),
    tertiary = TextColor(md_theme_light_tertiary),
    primaryInverse = TextColor(md_theme_light_inversePrimary),
)

internal fun darkTextColors(): TextColors = TextColors(
    primary = TextColor(md_theme_dark_primary),
    secondary = TextColor(md_theme_dark_secondary),
    tertiary = TextColor(md_theme_dark_tertiary),
    primaryInverse = TextColor(md_theme_dark_inversePrimary),
)

internal fun TextColors.withNames() = buildList {
    add(Pair("primary", primary))
    add(Pair("secondary", secondary))
    add(Pair("tertiary", tertiary))
    add(Pair("primaryInverse", primaryInverse))
}
