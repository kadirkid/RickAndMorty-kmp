package dev.kadirkid.rickandmorty.design.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.kadirkid.rickandmorty.design.core.SizeToken

@Composable
public expect fun AsyncImage(url: String, contentDescription: String, size: SizeToken, modifier: Modifier)