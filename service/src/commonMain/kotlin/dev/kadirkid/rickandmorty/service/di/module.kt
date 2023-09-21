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
package dev.kadirkid.rickandmorty.service.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import dev.kadirkid.rickandmorty.service.CharacterApi
import dev.kadirkid.rickandmorty.service.CharacterService
import dev.kadirkid.rickandmorty.service.GetCharacterByIdUseCase
import dev.kadirkid.rickandmorty.service.GetCharacterByIdUseCaseImpl
import dev.kadirkid.rickandmorty.service.GetCharactersUseCase
import dev.kadirkid.rickandmorty.service.GetCharactersUseCaseImpl
import dev.kadirkid.rickandmorty.util.utilModule
import org.koin.core.module.Module
import org.koin.dsl.module

private val apolloModule: Module = module {
    // Creates a client with 10MB MemoryCacheFactory
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .normalizedCache(MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024))
            .build()
    }
}

public fun serviceModule(): Module = module {
    includes(utilModule(), apolloModule)
    factory<CharacterApi> { CharacterService(get(), get()) }
    factory<GetCharacterByIdUseCase> { GetCharacterByIdUseCaseImpl(get()) }
    factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get()) }
}
