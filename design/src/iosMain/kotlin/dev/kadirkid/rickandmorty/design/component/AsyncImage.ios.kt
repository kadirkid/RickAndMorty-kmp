package dev.kadirkid.rickandmorty.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import dev.kadirkid.rickandmorty.design.core.SizeToken
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val httpClient = HttpClient(Darwin)

@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String,
    size: SizeToken,
    modifier: Modifier
) {
    val image: ImageBitmap? by produceState<ImageBitmap?>(null) {
        value = withContext(Dispatchers.Default) {
            val bytes = httpClient.get(url).readBytes()
            imageBitmapFromBytes(bytes)
        }
    }

    if (image != null) {
        Image(
            image!!,
            contentDescription,
            modifier = modifier.size(size.underlyingSize)
        )
    }
}

private fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap =
    org.jetbrains.skia.Image.makeFromEncoded(encodedImageData).toComposeImageBitmap()