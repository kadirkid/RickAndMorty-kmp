package dev.kadirkid.rickandmorty.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toPainter
import dev.kadirkid.rickandmorty.design.core.SizeToken
import java.net.URL
import javax.imageio.ImageIO

@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String,
    size: SizeToken,
    modifier: Modifier
) {
    Image(
        painter = loadImage(url),
        contentDescription = contentDescription,
        modifier = modifier.size(size.underlyingSize)
    )
}

private fun loadImage(url: String): Painter = ImageIO.read(URL(url)).toPainter()