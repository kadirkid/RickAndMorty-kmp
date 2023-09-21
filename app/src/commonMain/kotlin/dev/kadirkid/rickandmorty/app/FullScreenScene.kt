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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.main.MainState
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.core.color.LocalBackgroundColors
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

@Composable
public fun FullScreenScene(
    mainViewModel: MainViewModel,
    characterViewModel: CharacterViewModel,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            Button(
                onClick = { mainViewModel.reload() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) { Text(text = "Refresh") }
            Spacer(modifier = Modifier.size(8.dp))
            when (val state = mainViewModel.state.collectAsState().value) {
                is MainState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(text = state.message, fontSize = 64.sp)
                    }
                }

                is MainState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp),
                        )
                    }
                }

                is MainState.Success -> {
                    DividedScreen(
                        characterViewModel = characterViewModel,
                        characters = state.characters,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private fun DividedScreen(
    characterViewModel: CharacterViewModel,
    characters: List<SimpleCharacter>,
    modifier: Modifier = Modifier,
) {
    var selectedCharacter by remember { mutableStateOf(characters[0]) }

    Row(modifier = modifier) {
        var largestWidth by remember { mutableStateOf(0) }
        LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
            items(characters) {
                SimpleCard(
                    character = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .layout { measurable, constraints ->
                            val oldPlaceable = measurable.measure(constraints)
                            if (oldPlaceable.width > largestWidth) largestWidth = oldPlaceable.width

                            val newPlaceable = measurable.measure(
                                constraints.copy(
                                    minWidth = largestWidth,
                                    maxWidth = largestWidth,
                                ),
                            )
                            layout(newPlaceable.width, newPlaceable.height) {
                                newPlaceable.place(0, 0)
                            }
                        }
                        .clickable { selectedCharacter = it },
                )
            }
        }
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .weight(weight = 1f, fill = false)
                .fillMaxHeight()
                .background(color = LocalBackgroundColors.current.primary.color),
        )
        CharacterDetails(
            characterViewModel = characterViewModel,
            character = selectedCharacter,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        )
    }
}
