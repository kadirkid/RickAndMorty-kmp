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
package dev.kadirkid.rickandmorty.app.splitscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import dev.kadirkid.rickandmorty.app.ScreenType
import dev.kadirkid.rickandmorty.core.character.CharacterDetailState
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.design.CustomLazyColumn
import dev.kadirkid.rickandmorty.design.component.AsyncImage
import dev.kadirkid.rickandmorty.design.core.SizeToken
import dev.kadirkid.rickandmorty.design.core.TypographyToken
import dev.kadirkid.rickandmorty.design.core.color.LocalBackgroundColors
import dev.kadirkid.rickandmorty.design.core.color.LocalTextColors
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter

@Composable
internal fun CharacterDetailsScreen(
    characterViewModel: CharacterViewModel,
    characterId: String,
    screenType: ScreenType,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().background(LocalBackgroundColors.current.surface.color)
    ) {
        when (val state = characterViewModel.state.collectAsState().value) {
            is CharacterDetailState.Error -> {
                Text(
                    text = state.message,
                    color = LocalTextColors.current.primary.color,
                    fontSize = TypographyToken.Headline.Large.textStyle.fontSize,
                )
            }

            CharacterDetailState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            is CharacterDetailState.Success -> {
                FullCharacterScreen(
                    character = state.characters,
                    screenType = screenType,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }

        LaunchedEffect(characterId) {
            characterViewModel.fetch(characterId)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FullCharacterScreen(
    character: UniversalCharacter,
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize().padding(start = 16.dp),
    ) {
        when (screenType) {
            ScreenType.NONE -> {
                CustomLazyColumn { customModifier ->
                    stickyHeader {
                        Text(
                            text = character.name,
                            color = LocalTextColors.current.primary.color,
                            fontSize = TypographyToken.Headline.Small.textStyle.fontSize,
                            modifier = Modifier
                                .background(color = LocalBackgroundColors.current.surface.color)
                                .padding(8.dp)
                                .fillMaxWidth(),
                        )
                    }
                    item {
                        character.image?.let {
                            AsyncImage(
                                url = it,
                                contentDescription = "Character Image",
                                size = SizeToken.XXXXX_LARGE,
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .clip(CardDefaults.shape),
                            )
                        }
                    }
                    item {
                        StatusItem(statusType = "Species", value = character.species)
                    }
                    item {
                        StatusItem(statusType = "Gender", value = character.gender.value)
                    }
                    item {
                        StatusItem(statusType = "Type", value = character.type)
                    }

                    character.origin?.let {
                        item { StatusItem(statusType = "Origin", value = it.name) }
                    }
                    character.lastKnownLocation?.let {
                        item {
                            StatusItem(
                                statusType = "Location",
                                value = it.name
                            )
                        }
                    }
                    stickyHeader {
                        Text(
                            text = "Episodes",
                            color = LocalTextColors.current.primary.color,
                            fontSize = TypographyToken.Headline.Small.textStyle.fontSize,
                            modifier = Modifier
                                .background(color = LocalBackgroundColors.current.surface.color)
                                .padding(8.dp)
                                .fillMaxWidth(),
                        )
                    }
                    items(
                        character.episode.filter {
                            it.name != null &&
                                    it.episode != null &&
                                    it.airDate != null
                        },
                        key = { it.id },
                    ) {
                        Card(modifier = customModifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier.padding(start = 8.dp),
                            ) {
                                Text(
                                    text = it.episode!!,
                                    color = LocalTextColors.current.primary.color,
                                    fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        append(it.name!!)
                                        append(" · ")
                                        append(it.airDate!!)
                                    },
                                    color = LocalTextColors.current.secondary.color,
                                    fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                                    modifier = Modifier.padding(top = 4.dp, end = 8.dp),
                                )
                            }
                        }
                    }
                }
            }

            ScreenType.SPLIT -> {
                CharacterInformation(character = character, modifier = Modifier)
                Spacer(modifier = Modifier.weight(2f))
                EpisodesInformation(
                    character = character,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun EpisodesInformation(character: UniversalCharacter, modifier: Modifier = Modifier) {
    CustomLazyColumn(modifier = modifier.fillMaxSize()) { customModifier ->
        stickyHeader {
            Text(
                text = "Episodes",
                color = LocalTextColors.current.primary.color,
                fontSize = TypographyToken.Headline.Small.textStyle.fontSize,
                modifier = Modifier
                    .background(color = LocalBackgroundColors.current.surface.color)
                    .padding(8.dp)
                    .fillMaxWidth(),
            )
        }
        items(
            character.episode.filter {
                it.name != null &&
                        it.episode != null &&
                        it.airDate != null
            },
            key = { it.id },
        ) {
            Card(modifier = customModifier.padding(8.dp)) {
                Column(
                    modifier = Modifier.padding(start = 8.dp),
                ) {
                    Text(
                        text = it.episode!!,
                        color = LocalTextColors.current.primary.color,
                        fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(it.name!!)
                            append(" · ")
                            append(it.airDate!!)
                        },
                        color = LocalTextColors.current.secondary.color,
                        fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                        modifier = Modifier.padding(top = 4.dp, end = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterInformation(character: UniversalCharacter, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight()) {
        character.image?.let {
            AsyncImage(
                url = it,
                contentDescription = "Character Image",
                size = SizeToken.XXXXX_LARGE,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(CardDefaults.shape),
            )
        }
        StatusItem(statusType = "Name", value = character.name)
        StatusItem(statusType = "Species", value = character.species)
        StatusItem(statusType = "Gender", value = character.gender.value)
        StatusItem(statusType = "Type", value = character.type)
        character.origin?.let { StatusItem(statusType = "Origin", value = it.name) }
        character.lastKnownLocation?.let { StatusItem(statusType = "Location", value = it.name) }
    }
}

@Composable
internal fun StatusItem(statusType: String, value: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = "$statusType:",
            color = LocalTextColors.current.primary.color,
            fontSize = TypographyToken.Label.Large.textStyle.fontSize,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.weight(1f, fill = false).width(1.dp))
        Text(
            text = value,
            color = LocalTextColors.current.secondary.color,
            fontSize = TypographyToken.Label.Large.textStyle.fontSize,
            modifier = Modifier.padding(8.dp),
        )
    }
}
