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
    public val results: T,
)
