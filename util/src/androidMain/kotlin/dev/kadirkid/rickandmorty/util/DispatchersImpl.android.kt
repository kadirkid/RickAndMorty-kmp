package dev.kadirkid.rickandmorty.util

import kotlinx.coroutines.CoroutineDispatcher

internal actual class DispatchersImpl : Dispatchers {
    actual override val main: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Main
    actual override val IO: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.IO
    actual override val default: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Default
}