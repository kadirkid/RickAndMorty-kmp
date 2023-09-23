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
package dev.kadirkid.rickandmorty.core.main

import com.apollographql.apollo3.exception.ApolloCompositeException
import dev.kadirkid.rickandmorty.service.GetCharactersUseCase
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public interface MainViewModel {
    public val state: StateFlow<MainState>

    public fun fetch()
    public fun reload()
}

internal class MainViewModelImpl(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val mainScope: CoroutineScope,
) : MainViewModel {
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    override val state: StateFlow<MainState> = _state.asStateFlow()

    override fun fetch() = loadData()

    override fun reload() = loadData()

    private fun loadData() {
        _state.value = MainState.Loading
        mainScope.launch {
            getCharactersUseCase.execute()
                .onSuccess { characters ->
                    _state.value = MainState.Success(characters)
                    println("--------------> CHARACTERS: ${characters.map { it.name }}")
                }
                .onFailure { throwable ->
                    _state.value = MainState.Error(throwable.message ?: "Unknown error")
                    if (throwable is ApolloCompositeException) {
                        throwable.suppressedExceptions.forEach { exception ->
                            println("--------------> SUPPRESSED EXCEPTION: $exception")
                        }
                    } else {
                        println("--------------> CHARACTERS FAILED WITH: $throwable")
                    }
                }
        }
    }
}

public sealed class MainState {
    public data object Loading : MainState()
    public class Success(public val characters: List<SimpleCharacter>) : MainState()
    public class Error(public val message: String) : MainState()
}
