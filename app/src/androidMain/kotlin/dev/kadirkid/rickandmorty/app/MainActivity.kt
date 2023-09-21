package dev.kadirkid.rickandmorty.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.RickAndMortyTheme
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

public class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) mainViewModel.fetch()
        setContent {
            RickAndMortyTheme {
                CharacterListScene(viewModel = mainViewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}