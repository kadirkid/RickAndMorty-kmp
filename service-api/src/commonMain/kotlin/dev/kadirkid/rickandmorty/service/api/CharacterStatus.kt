package dev.kadirkid.rickandmorty.service.api

/**
 * Class that represents the status of a character in the Rick and Morty universe.
 */
public enum class CharacterStatus(public val value: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown")
}