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
package dev.kadirkid.rickandmorty.design.core

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.skiko.loadBytesFromPath

private var InterFontFamily: FontFamily = FontFamily.Default

public suspend fun initializeFonts() {
    InterFontFamily = loadInterFont()
}

private suspend fun loadInterFont(): FontFamily {
    return FontFamily(
        Font("inter_normal", data = loadBytesFromPath("font/inter_regular.otf"), FontWeight.Normal),
        Font("inter_medium", data = loadBytesFromPath("font/inter_medium.otf"), FontWeight.Medium),
        Font("inter_semibold", data = loadBytesFromPath("font/inter_semibold.otf"), FontWeight.SemiBold),
        Font("inter_bold", data = loadBytesFromPath("font/inter_bold.otf"), FontWeight.Bold),
    )
}

internal actual val platformTextStyle: PlatformTextStyle = PlatformTextStyle(null, null)
internal actual val fontFamily: FontFamily = InterFontFamily
