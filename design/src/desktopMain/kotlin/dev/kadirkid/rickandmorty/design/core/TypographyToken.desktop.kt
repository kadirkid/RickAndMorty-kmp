package dev.kadirkid.rickandmorty.design.core

import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

private val InterFontFamily = FontFamily(
    Font(resource = "font/inter_regular.otf", FontWeight.Normal),
    Font(resource = "font/inter_medium.otf", FontWeight.Medium),
    Font(resource = "font/inter_semibold.otf", FontWeight.SemiBold),
    Font(resource = "font/inter_bold.otf", FontWeight.Bold),
)

internal actual val fontFamily: FontFamily = InterFontFamily
internal actual val platformTextStyle: PlatformTextStyle = PlatformTextStyle(
    spanStyle = PlatformSpanStyle.Default,
    paragraphStyle = PlatformParagraphStyle.Default,
)