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
    private val mainScope: CoroutineScope
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
                    println("--------------> CHARACTERS: $characters")
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