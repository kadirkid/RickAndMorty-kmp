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

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import dev.kadirkid.rickandmorty.AllCharactersQuery
import dev.kadirkid.rickandmorty.CharacterQuery
import dev.kadirkid.rickandmorty.service.api.Pagination
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter
import dev.kadirkid.rickandmorty.util.Dispatchers
import kotlinx.coroutines.withContext

internal class CharacterService(
    private val apolloClient: ApolloClient,
    private val dispatchers: Dispatchers,
) : CharacterApi {
    override suspend fun getAllCharacters(page: Int): Result<Pagination<List<SimpleCharacter>>> =
        runCatching {
            val result = withContext(dispatchers.IO) {
                apolloClient
                    .query(AllCharactersQuery(Optional.present(page)))
                    .execute()
            }
            result
                .data
                ?.characters
                ?.takeIf { it.results != null }
                ?.let {
                    Pagination(
                        count = it.info?.count,
                        pages = it.info?.pages,
                        next = it.info?.next,
                        prev = it.info?.prev,
                        results = it.results!!.mapToSimpleCharacter(),
                    )
                } ?: throw result.getError()
        }

    override suspend fun getAllCharacter(page: Int): Either<Throwable, Pagination<List<SimpleCharacter>>> {
        val result = withContext(dispatchers.IO) {
            apolloClient
                .query(AllCharactersQuery(Optional.present(page)))
                .execute()
        }
        return result
            .data
            ?.characters
            ?.takeIf { it.results != null }
            ?.let {
                Pagination(
                    count = it.info?.count,
                    pages = it.info?.pages,
                    next = it.info?.next,
                    prev = it.info?.prev,
                    results = it.results!!.mapToSimpleCharacter(),
                )
            }?.right() ?: result.getError().left()
    }

    override suspend fun getCharacter(id: String): Result<UniversalCharacter> = runCatching {
        val result = withContext(dispatchers.IO) {
            apolloClient
                .query(CharacterQuery(id))
                .execute()
        }

        result
            .data
            ?.character
            ?.toUniversalCharacter() ?: throw result.getError()
    }
}
