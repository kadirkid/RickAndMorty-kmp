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
import io.ktor.client.engine.js.JsClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image

private val httpClient = HttpClient(JsClient())

@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String,
    size: SizeToken,
    modifier: Modifier,
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
            modifier = modifier.size(size.underlyingSize),
        )
    }
}

private fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap =
    Image.makeFromEncoded(encodedImageData).toComposeImageBitmap()
