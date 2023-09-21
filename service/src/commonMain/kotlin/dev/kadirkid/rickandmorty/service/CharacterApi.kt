package dev.kadirkid.rickandmorty.service

import dev.kadirkid.rickandmorty.service.api.Pagination
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter

public interface CharacterApi {
    public suspend fun getAllCharacters(): Result<Pagination<List<SimpleCharacter>>>
    public suspend fun getCharacter(id: String): Result<UniversalCharacter>
}