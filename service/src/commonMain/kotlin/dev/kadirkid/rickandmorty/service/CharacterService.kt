package dev.kadirkid.rickandmorty.service

import com.apollographql.apollo3.ApolloClient
import dev.kadirkid.rickandmorty.AllCharactersQuery
import dev.kadirkid.rickandmorty.CharacterQuery
import dev.kadirkid.rickandmorty.service.api.Pagination
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter
import dev.kadirkid.rickandmorty.util.Dispatchers
import kotlinx.coroutines.withContext

internal class CharacterService(
    private val apolloClient: ApolloClient,
    private val dispatchers: Dispatchers
) : CharacterApi {
    override suspend fun getAllCharacters(): Result<Pagination<List<SimpleCharacter>>> =
        runCatching {
            val result = withContext(dispatchers.IO) {
                apolloClient
                    .query(AllCharactersQuery())
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
                        results = it.results!!.mapToSimpleCharacter()
                    )
                } ?: throw result.getError()
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