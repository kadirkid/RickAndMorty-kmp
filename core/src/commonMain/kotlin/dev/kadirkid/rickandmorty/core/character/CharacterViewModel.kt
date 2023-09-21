package dev.kadirkid.rickandmorty.core.character

import dev.kadirkid.rickandmorty.service.GetCharacterByIdUseCase
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.scope.ScopeID

public interface CharacterViewModel {
    public val state: StateFlow<CharacterDetailState>
    public fun fetch(id: String)
    public fun reload(id: String)
}

internal class CharacterViewModelImpl(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val mainScope: CoroutineScope
) : CharacterViewModel {
    private val _state = MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    override val state: StateFlow<CharacterDetailState> = _state.asStateFlow()

    override fun fetch(id: String) = loadData(id)

    override fun reload(id: String) = loadData(id)

    private fun loadData(id: String) {
        mainScope.launch {
            getCharacterByIdUseCase.execute(id)
                .onSuccess {
                    _state.value = CharacterDetailState.Success(it)
                    println("--------------> UNIV CHARACTER: ${it.name}")
                }
                .onFailure {
                    _state.value = CharacterDetailState.Error(it.message ?: "")
                    println("--------------> UNIV CHARACTER ERROR: ${it.message}")
                }
        }
    }
}

public sealed class CharacterDetailState {
    public data object Loading : CharacterDetailState()
    public class Success(public val characters: UniversalCharacter) : CharacterDetailState()
    public class Error(public val message: String) : CharacterDetailState()
}