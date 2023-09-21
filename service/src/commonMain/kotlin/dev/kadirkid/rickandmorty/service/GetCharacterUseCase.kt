package dev.kadirkid.rickandmorty.service

import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

public interface GetCharactersUseCase {
    /**
     * Returns a list of characters.
     *
     * @param filterType The type of filter to apply to the list.
     * @param forceRefresh Whether to force a refresh of the data.
     * @return The list of characters.
     */
    public suspend fun execute(
        filterType: CharacterSortType = CharacterSortType.DEFAULT,
        forceRefresh: Boolean = false
    ): Result<List<SimpleCharacter>>
}

internal class GetCharactersUseCaseImpl(private val characterApi: CharacterApi) :
    GetCharactersUseCase {
    override suspend fun execute(
        filterType: CharacterSortType,
        forceRefresh: Boolean
    ): Result<List<SimpleCharacter>> = characterApi.getAllCharacters().fold(
        onSuccess = { pagination -> Result.success(pagination.results.sorted(filterType)) },
        onFailure = { error -> Result.failure(error) }
    )
}

private fun List<SimpleCharacter>.sorted(
    filterType: CharacterSortType
): List<SimpleCharacter> = when (filterType) {
    CharacterSortType.DEFAULT -> this
    CharacterSortType.NAME -> sortedBy { it.name }
    CharacterSortType.GENDER -> sortedBy { it.gender }
    CharacterSortType.STATUS -> sortedBy { it.status }
    CharacterSortType.ORIGIN -> sortedBy { it.origin?.name }
    CharacterSortType.LAST_KNOWN_LOCATION -> sortedBy { it.lastKnownLocation?.name }
}

public enum class CharacterSortType {
    DEFAULT, NAME, GENDER, STATUS, ORIGIN, LAST_KNOWN_LOCATION
}