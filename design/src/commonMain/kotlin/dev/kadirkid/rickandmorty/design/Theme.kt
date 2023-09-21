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
package dev.kadirkid.rickandmorty.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import dev.kadirkid.rickandmorty.design.core.TypographyToken
import dev.kadirkid.rickandmorty.design.core.color.LocalBackgroundColors
import dev.kadirkid.rickandmorty.design.core.color.LocalTextColors
import dev.kadirkid.rickandmorty.design.core.color.colorPalette
import dev.kadirkid.rickandmorty.design.core.color.materialColors

private val typography = Typography(
    // for now, leave defaults for display{Large,Medium,Small}
    headlineLarge = TypographyToken.Headline.Large.textStyle,
    headlineMedium = TypographyToken.Headline.Medium.textStyle,
    headlineSmall = TypographyToken.Headline.Small.textStyle,
    titleLarge = TypographyToken.Title.Large.textStyle,
    titleMedium = TypographyToken.Title.Medium.textStyle,
    titleSmall = TypographyToken.Title.Small.textStyle,
    bodyLarge = TypographyToken.Body.Large.textStyle,
    bodyMedium = TypographyToken.Body.Medium.textStyle,
    bodySmall = TypographyToken.Body.Small.textStyle,
    labelLarge = TypographyToken.Label.Large.textStyle,
    labelMedium = TypographyToken.Label.Medium.textStyle,
    labelSmall = TypographyToken.Label.Small.textStyle,
)

private val LocalThemeAlreadyApplied = compositionLocalOf { false }

@Composable
public fun RickAndMortyTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    if (LocalThemeAlreadyApplied.current) {
        content()
    } else {
        val colorPalette = colorPalette(useDarkTheme)
        val materialColors = materialColors(useDarkTheme)

        CompositionLocalProvider(
            LocalTextColors provides colorPalette.textColors,
            LocalBackgroundColors provides colorPalette.backgroundColors,
            LocalThemeAlreadyApplied provides true,
        ) {
            MaterialTheme(
                colorScheme = materialColors,
                typography = typography,
                content = content,
            )
        }
    }
}
