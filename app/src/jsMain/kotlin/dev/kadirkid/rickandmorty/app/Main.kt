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

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.di.initKoin
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.RickAndMortyTheme
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

private val logger = object : Logger(Level.ERROR) {
    override fun display(level: Level, msg: MESSAGE) {
        println("$level: $msg")
    }
}

private val koin = initKoin {
    logger(logger)
}.koin

@OptIn(ExperimentalComposeUiApi::class)
public fun main() {
    val mainViewModel = koin.get<MainViewModel>()
    val characterViewModel = koin.get<CharacterViewModel>()

    onWasmReady {
        CanvasBasedWindow {
            RickAndMortyTheme { FullScreenScene(mainViewModel, characterViewModel) }
            LaunchedEffect(Unit) { mainViewModel.fetch() }
        }
    }
}
