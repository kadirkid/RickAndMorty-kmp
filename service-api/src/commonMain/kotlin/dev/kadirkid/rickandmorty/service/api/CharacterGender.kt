package dev.kadirkid.rickandmorty.service.api

/**
 * Class that represents the gender of a character in the Rick and Morty universe.
 */
public enum class CharacterGender(public val value: String) {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown")
}