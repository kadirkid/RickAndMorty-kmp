package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko

/**
 * Class that represents only the name of a location in the Rick and Morty universe.
 */
@Poko
public class SimpleLocation(private val internalName: String?) {
    public val name: String
        get() = internalName ?: "Unknown"
}