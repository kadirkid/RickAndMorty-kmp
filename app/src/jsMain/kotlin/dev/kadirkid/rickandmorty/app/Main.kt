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
        println("${level}: $msg")
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