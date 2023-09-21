package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko

/**
 * Class containing minimum information about a character in the Rick and Morty universe.
 */
@Poko
public class SimpleCharacter(
    /**
     * The id of the character.
     */
    public val id: String,
    /**
     * The name of the character.
     */
    public val name: String?,
    /**
     * The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
     */
    public val gender: CharacterGender?,
    /**
     * The status of the character ('Alive', 'Dead' or 'unknown').
     */
    public val status: CharacterStatus?,
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