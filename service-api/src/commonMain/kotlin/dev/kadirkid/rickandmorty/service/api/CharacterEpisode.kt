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

@Poko
public class CharacterEpisode(
    /**
     * The id of the episode.
     */
    public val id: String,
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
