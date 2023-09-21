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
        forceRefresh: Boolean = false,
    ): Result<List<SimpleCharacter>>
}

internal class GetCharactersUseCaseImpl(private val characterApi: CharacterApi) :
    GetCharactersUseCase {
    override suspend fun execute(
        filterType: CharacterSortType,
        forceRefresh: Boolean,
    ): Result<List<SimpleCharacter>> = characterApi.getAllCharacters().fold(
        onSuccess = { pagination -> Result.success(pagination.results.sorted(filterType)) },
        onFailure = { error -> Result.failure(error) },
    )
}

private fun List<SimpleCharacter>.sorted(
    filterType: CharacterSortType,
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
