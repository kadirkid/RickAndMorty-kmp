package dev.kadirkid.rickandmorty.service

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import dev.kadirkid.rickandmorty.AllCharactersQuery
import dev.kadirkid.rickandmorty.CharacterQuery
import dev.kadirkid.rickandmorty.service.api.CharacterEpisode
import dev.kadirkid.rickandmorty.service.api.CharacterGender
import dev.kadirkid.rickandmorty.service.api.CharacterStatus
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import dev.kadirkid.rickandmorty.service.api.SimpleLocation
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter

internal fun <D : Operation.Data> ApolloResponse<D>.getError(): Throwable {
    return errors?.let { ApolloResponseException(it) }
        ?: RuntimeException("Something went wrong :sadparrot")
}

internal fun CharacterQuery.Character.toUniversalCharacter(): UniversalCharacter? {
    return UniversalCharacter(
        id = id ?: return null, // ID is mandatory
        name = name,
        gender = gender?.let { gender ->
            CharacterGender.entries.first { it.value == gender }
        },
        status = status?.let { status ->
            CharacterStatus.entries.first { it.value == status }
        },
        image = image,
        origin = origin?.let { SimpleLocation(it.name) },
        lastKnownLocation = location?.let { SimpleLocation(it.name) },
        species = species,
        type = type,
        episode = episode.mapNotNull { episode ->
            episode ?: return@mapNotNull null
            CharacterEpisode(
                id = episode.id,
                name = episode.name,
                airDate = episode.air_date,
                episode = episode.episode
            )
        },
    )
}


internal fun List<AllCharactersQuery.Result?>.mapToSimpleCharacter(): List<SimpleCharacter> =
    mapNotNull { result ->
        result ?: return@mapNotNull null
        SimpleCharacter(
            id = result.id ?: return@mapNotNull null, // ID is mandatory
            name = result.name,
            gender = result.gender?.let { gender ->
                CharacterGender.entries.first { it.value == gender }
            },
            status = result.status?.let { status ->
                CharacterStatus.entries.first { it.value == status }
            },
            image = result.image,
            origin = result.origin?.let { SimpleLocation(it.name) },
            lastKnownLocation = result.location?.let { SimpleLocation(it.name) }
        )
    }