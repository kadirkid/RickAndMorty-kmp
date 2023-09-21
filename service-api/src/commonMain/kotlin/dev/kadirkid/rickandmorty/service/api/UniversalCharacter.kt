package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko

@Poko
public class UniversalCharacter(
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
     * The species of the character.
     */
    public val species: String?,
    /**
     * The type or subspecies of the character.
     */
    public val type: String?,
    /**
     * Episodes in which this character appeared.
     */
    public val episode: List<CharacterEpisode>?,
    /**
     * The character's origin location
     */
    public val origin: SimpleLocation?,
    /**
     * The character's last known location
     */
    public val lastKnownLocation: SimpleLocation?,
)
