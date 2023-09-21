package dev.kadirkid.rickandmorty.util

import kotlinx.coroutines.CoroutineDispatcher

internal expect class DispatchersImpl: Dispatchers {
    override val main: CoroutineDispatcher
    override val IO: CoroutineDispatcher
    override val default: CoroutineDispatcher
}