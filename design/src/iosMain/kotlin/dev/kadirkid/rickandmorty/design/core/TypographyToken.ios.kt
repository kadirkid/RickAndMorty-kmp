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
        Typeface(loadCustomFont("inter_regular"))
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