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
package dev.kadirkid.rickandmorty.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.kadirkid.rickandmorty.core.character.CharacterDetailState
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.design.component.AsyncImage
import dev.kadirkid.rickandmorty.design.core.SizeToken
import dev.kadirkid.rickandmorty.design.core.TypographyToken
import dev.kadirkid.rickandmorty.design.core.color.LocalTextColors
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter
import dev.kadirkid.rickandmorty.service.api.UniversalCharacter

@Composable
internal fun CharacterDetails(
    characterViewModel: CharacterViewModel,
    character: SimpleCharacter,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        when (val state = characterViewModel.state.collectAsState().value) {
            is CharacterDetailState.Error -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                ) {
                    Text(
                        text = state.message,
                        color = LocalTextColors.current.primary.color,
                        fontSize = TypographyToken.Headline.Large.textStyle.fontSize,
                    )
                }
            }

            CharacterDetailState.Loading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(16.dp),
                    )
                }
            }

            is CharacterDetailState.Success -> {
                FullCharacterScreen(
                    character = state.characters,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }

        LaunchedEffect(character) {
            characterViewModel.fetch(character.id)
        }
    }
}

@Composable
private fun FullCharacterScreen(character: UniversalCharacter, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        character.image?.let {
            AsyncImage(
                url = it,
                contentDescription = "Character Image",
                size = SizeToken.XXXXX_LARGE,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }

        character.name?.let {
            Text(
                text = it,
                color = LocalTextColors.current.primary.color,
                fontSize = TypographyToken.Headline.Large.textStyle.fontSize,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }

        character.origin?.let {
            Text(
                text = it.name,
                color = LocalTextColors.current.secondary.color,
                fontSize = TypographyToken.Headline.Small.textStyle.fontSize,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }

        character.species?.let {
            Text(
                text = it,
                color = LocalTextColors.current.secondary.color,
                fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
            )
        }

        character.gender?.let {
            Text(
                text = it.value,
                color = LocalTextColors.current.secondary.color,
                fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
            )
        }
        Text(
            text = character.type ?: "Unknown",
            color = LocalTextColors.current.secondary.color,
            fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Start),
        )
        character.lastKnownLocation?.let {
            Text(
                text = it.name,
                color = LocalTextColors.current.secondary.color,
                fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start),
            )
        }
    }
}
