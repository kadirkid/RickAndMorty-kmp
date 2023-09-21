package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko

@Poko
public class CharacterEpisode(
    /**
     * The id of the episode.
     */
    public val id: String?,
    /**
     * The name of the episode.
     */
    public val name: String?,
    /**
     * The air date of the episode.
     */
    public val airDate: String?,
    /**
     * The code of the episode.
     */
    public val episode: String?,
)
