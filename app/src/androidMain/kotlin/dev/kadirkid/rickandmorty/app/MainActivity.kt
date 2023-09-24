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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kadirkid.rickandmorty.app.splitscreen.CharacterDetailsScreen
import dev.kadirkid.rickandmorty.app.splitscreen.HomeScreen
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.RickAndMortyTheme
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

public class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by inject { parametersOf(this) }
    private val characterViewModel: CharacterViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "CharacterList",
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    composable(START_DESTINATION_ROUTE) {
                        HomeScreen(
                            mainViewModel,
                            onClick = {
                                navController.navigate(
                                    route = CHARACTER_DETAILS_ROUTE.replace(
                                        "{$CHARACTER_ID}",
                                        it,
                                    ),
                                )
                            },
                            screenType = ScreenType.NONE,
                        )
                    }
                    composable(CHARACTER_DETAILS_ROUTE) {
                        val id = it.arguments?.getString(CHARACTER_ID)!!
                        CharacterDetailsScreen(
                            characterViewModel = characterViewModel,
                            characterId = id,
                            screenType = ScreenType.NONE,
                        )
                    }
                }
            }
        }
    }

    private companion object {
        const val CHARACTER_ID = "characterId"
        const val START_DESTINATION_ROUTE = "CharacterList"
        const val CHARACTER_DETAILS_ROUTE = "CharacterDetails/{$CHARACTER_ID}"
    }
}
