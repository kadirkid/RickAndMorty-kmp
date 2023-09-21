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

import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface

private fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}

internal actual val fontFamily: FontFamily
    get() = FontFamily(
        Typeface(loadCustomFont("inter_regular")),
    )

internal actual val platformTextStyle: PlatformTextStyle
    get() = PlatformTextStyle(
        spanStyle = PlatformSpanStyle.Default,
        paragraphStyle = PlatformParagraphStyle.Default,
    )
/**
 * TODO: Revisit this:
 *
 *         Typeface(loadCustomFont("inter_medium")),
 *         Typeface(loadCustomFont("inter_semibold")),
 *         Typeface(loadCustomFont("inter_bold"))
 */
