/**
 * Copyright 2024 Abdulahi Osoble
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import dev.kadirkid.rickandmorty.app.ScreenType
import dev.kadirkid.rickandmorty.app.splitscreen.HomeScreen
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.di.initKoin
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.RickAndMortyTheme
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import platform.UIKit.UIViewController

private val logger = object : Logger(Level.ERROR) {
    override fun display(level: Level, msg: MESSAGE) {
        println("$level: $msg")
    }
}

private val koin = initKoin {
    logger(logger)
}.koin

public fun MainViewController(): UIViewController = ComposeUIViewController {
    App(koin.get<MainViewModel>(), koin.get<CharacterViewModel>())
}

@Composable
private fun App(
    mainViewModel: MainViewModel,
    characterViewModel: CharacterViewModel,
) {
    Surface(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
        RickAndMortyTheme {
            HomeScreen(
                mainViewModel = mainViewModel,
                characterViewModel = characterViewModel,
                screenType = ScreenType.NONE,
            )
        }
    }
}
