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
