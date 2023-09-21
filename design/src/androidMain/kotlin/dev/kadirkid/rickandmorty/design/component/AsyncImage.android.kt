package dev.kadirkid.rickandmorty.design.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import dev.kadirkid.rickandmorty.design.core.SizeToken
import coil.compose.AsyncImage as CoilAsyncImage

@Composable
actual fun AsyncImage(
    url: String,
    contentDescription: String,
    size: SizeToken,
    modifier: Modifier
) {
    CoilAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier.size(size.underlyingSize)
    )
}