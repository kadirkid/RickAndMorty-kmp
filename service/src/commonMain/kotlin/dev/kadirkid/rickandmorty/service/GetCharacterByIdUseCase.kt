package dev.kadirkid.rickandmorty.service

import dev.kadirkid.rickandmorty.service.api.UniversalCharacter

public interface GetCharacterByIdUseCase {
    /**
     * Returns a character by id.
     *
     * @param id The id of the character.
     * @return The character with the given id.
     */
    public suspend fun execute(id: String): Result<UniversalCharacter>
}

internal class GetCharacterByIdUseCaseImpl(private val api: CharacterApi) : GetCharacterByIdUseCase {
    override suspend fun execute(id: String): Result<UniversalCharacter> = api.getCharacter(id)
}