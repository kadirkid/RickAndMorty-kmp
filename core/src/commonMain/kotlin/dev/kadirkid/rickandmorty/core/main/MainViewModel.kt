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
@file:Suppress("TYPE_MISMATCH")

package dev.kadirkid.rickandmorty.core.main

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import com.apollographql.apollo3.exception.ApolloCompositeException
import dev.kadirkid.rickandmorty.service.GetCharactersUseCase
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

public interface MainViewModel {
    public val pagingFlow: Flow<PagingData<SimpleCharacter>>
}

internal class MainViewModelImpl(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val mainScope: CoroutineScope,
) : MainViewModel {
    private val pager: Pager<Int, SimpleCharacter> = Pager(
        config = PagingConfig(pageSize = 20, initialLoadSize = 20),
        initialKey = null,
        pagingSourceFactory = { CharacterListDataSource(useCase = getCharactersUseCase) },
    )
    override val pagingFlow: Flow<PagingData<SimpleCharacter>> = pager.flow

    private class CharacterListDataSource(
        private val useCase: GetCharactersUseCase,
    ) : PagingSource<Int, SimpleCharacter>() {
        override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, SimpleCharacter> {
            val page = params.key ?: FIRST_PAGE_INDEX
            val result = useCase.execute(page = page)
            val r = useCase.invoke(page = page)
            if (r.isRight()) {
                r.toIor().isRight()
            }
            return try {
                val data = result.getOrThrow()
                println("--------------> PAGE: $page\nDATA: ${data.results.map { it.name }}\n")
                PagingSourceLoadResultPage(
                    data = data.results,
                    prevKey = data.prev,
                    nextKey = data.next,
                )
            } catch (e: Exception) {
                if (e is ApolloCompositeException) {
                    e.suppressedExceptions.forEach { exception ->
                        println("--------------> SUPPRESSED EXCEPTION: $exception")
                    }
                } else {
                    println("--------------> CHARACTERS FAILED WITH: $e")
                }

                PagingSourceLoadResultError<Int, SimpleCharacter>(
                    Exception("Whoops! Something went wrong: \n$e"),
                )
            }
        }

        override fun getRefreshKey(state: PagingState<Int, SimpleCharacter>): Int? = state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

        private companion object {
            const val FIRST_PAGE_INDEX = 1
        }
    }
}

public sealed class MainState {
    public data object Loading : MainState()
    public class Success(public val characters: List<SimpleCharacter>) : MainState()
    public class Error(public val message: String) : MainState()
}
