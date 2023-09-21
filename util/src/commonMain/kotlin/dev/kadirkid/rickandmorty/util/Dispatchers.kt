package dev.kadirkid.rickandmorty.util

import kotlinx.coroutines.CoroutineDispatcher

public interface Dispatchers {
    public val main: CoroutineDispatcher
    public val IO: CoroutineDispatcher
    public val default: CoroutineDispatcher
}