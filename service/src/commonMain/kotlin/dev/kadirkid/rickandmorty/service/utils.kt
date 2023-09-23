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
package dev.kadirkid.rickandmorty.service

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import dev.kadirkid.rickandmorty.AllCharactersQuery
import dev.kadirkid.rickandmorty.CharacterQuery
import dev.kadirkid.rickandmorty.service.Constant.UNKNOWN
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
        name = name ?: UNKNOWN,
        gender = gender?.let { gender ->
            CharacterGender.entries.first { it.value == gender }
        } ?: CharacterGender.UNKNOWN,
        status = status?.let { status ->
            CharacterStatus.entries.first { it.value == status }
        } ?: CharacterStatus.UNKNOWN,
        image = image,
        origin = origin?.let { SimpleLocation(it.name) },
        lastKnownLocation = location?.let { SimpleLocation(it.name) },
        species = species ?: UNKNOWN,
        type = type.takeIf { !it.isNullOrEmpty() } ?: UNKNOWN,
        episode = episode.mapNotNull { episode ->
            episode ?: return@mapNotNull null
            CharacterEpisode(
                id = episode.id!!,
                name = episode.name,
                airDate = episode.air_date,
                episode = episode.episode,
            )
        },
    )
}

internal fun List<AllCharactersQuery.Result?>.mapToSimpleCharacter(): List<SimpleCharacter> =
    mapNotNull { result ->
        result ?: return@mapNotNull null
        SimpleCharacter(
            id = result.id ?: return@mapNotNull null, // ID is mandatory
            name = result.name ?: UNKNOWN,
            gender = result.gender?.let { gender ->
                CharacterGender.entries.first { it.value == gender }
            }?: CharacterGender.UNKNOWN,
            status = result.status?.let { status ->
                CharacterStatus.entries.first { it.value == status }
            }?: CharacterStatus.UNKNOWN,
            image = result.image,
            origin = result.origin?.let { SimpleLocation(it.name) },
            lastKnownLocation = result.location?.let { SimpleLocation(it.name) },
        )
    }
