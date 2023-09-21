package dev.kadirkid.rickandmorty.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun dispatchers(): Dispatchers

public fun utilModule(): Module = module {
    single<Dispatchers> { dispatchers() }
    single<CoroutineScope> { MainScope() }
}