package dev.kadirkid.rickandmorty.design.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SizeToken(val underlyingSize: Dp) {
    SMALL(24.dp),
    MEDIUM(32.dp),
    LARGE(48.dp),
    X_LARGE(64.dp),
    XX_LARGE(96.dp),
    XXX_LARGE(128.dp),
    XXXX_LARGE(192.dp),
    XXXXX_LARGE(256.dp),
    XXXXXX_LARGE(384.dp),
}