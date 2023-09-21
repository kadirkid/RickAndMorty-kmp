package dev.kadirkid.rickandmorty.service.api

import dev.drewhamilton.poko.Poko

/**
 * Class that represents the pagination of a response.
 */
@Poko
public class Pagination<T>(
    /**
     * The length of the response.
     */
    public val count: Int?,
    /**
     * The amount of pages.
     */
    public val pages: Int?,
    /**
     * Number of the next page (if it exists)
     */
    public val next: Int?,
    /**
     * Number of the previous page (if it exists)
     */
    public val prev: Int?,
    /**
     * The list of results.
     */
    public val results: T
)
