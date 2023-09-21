package dev.kadirkid.rickandmorty.app

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.kadirkid.rickandmorty.core.character.CharacterViewModel
import dev.kadirkid.rickandmorty.core.di.initKoin
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.RickAndMortyTheme
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

public fun main() {
    val mainViewModel = koin.get<MainViewModel>()
    val characterViewModel = koin.get<CharacterViewModel>()
    application {
        Window(
            state = rememberWindowState(size = DpSize(1280.dp, 800.dp)),
            title = "SDUI Demo",
            onCloseRequest = ::exitApplication,
        ) {
            RickAndMortyTheme { FullScreenScene(mainViewModel, characterViewModel) }
        }

        LaunchedEffect(Unit) {
            mainViewModel.fetch()
        }
    }
}