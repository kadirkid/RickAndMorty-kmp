package dev.kadirkid.rickandmorty.core.di

import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.character.CharacterViewModelImpl
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.core.main.MainViewModelImpl
import dev.kadirkid.rickandmorty.service.di.serviceModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

public fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication = startKoin {
    appDeclaration()
    modules(mainModule())
    modules(characterModule())
}

// called by iOS etc
public fun initKoin(): KoinApplication = initKoin() {}

public fun mainModule(): Module = module {
    includes(serviceModule())
    factory<MainViewModel> { MainViewModelImpl(get(), get()) }
}

public fun characterModule(): Module = module {
    includes(serviceModule())
    factory<CharacterViewModel> { CharacterViewModelImpl(get(), get()) }
}