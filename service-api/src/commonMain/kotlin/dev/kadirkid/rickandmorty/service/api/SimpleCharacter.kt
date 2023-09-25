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
package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko
import kotlinx.serialization.Serializable

/**
 * Class containing minimum information about a character in the Rick and Morty universe.
 */
@Poko
@Serializable
public class SimpleCharacter(
    /**
     * The id of the character.
     */
    public val id: String,
    /**
     * The name of the character.
     */
    public val name: String,
    /**
     * The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
     */
    public val gender: CharacterGender,
    /**
     * The status of the character ('Alive', 'Dead' or 'unknown').
     */
    public val status: CharacterStatus,
    /**
     * Link to the character's image.
     * All images are 300x300px and most are medium shots or portraits since they are intended to be
     * used as avatars.
     */
    public val image: String?,
    /**
     * The character's origin location
     */
    public val origin: SimpleLocation?,
    /**
     * The character's last known location
     */
    public val lastKnownLocation: SimpleLocation?,
)
