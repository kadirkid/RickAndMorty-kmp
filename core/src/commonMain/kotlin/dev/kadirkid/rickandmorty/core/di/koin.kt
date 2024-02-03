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
public fun initKoin(): KoinApplication = initKoin {}

public fun mainModule(): Module = module {
    includes(serviceModule())
    factory<MainViewModel> { MainViewModelImpl(get(), get()) }
}

public fun characterModule(): Module = module {
    includes(serviceModule())
    factory<CharacterViewModel> { CharacterViewModelImpl(get(), get()) }
}
