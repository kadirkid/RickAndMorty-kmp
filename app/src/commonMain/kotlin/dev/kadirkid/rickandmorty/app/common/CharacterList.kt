package dev.kadirkid.rickandmorty.app.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import dev.kadirkid.rickandmorty.app.ScreenType
import dev.kadirkid.rickandmorty.app.SimpleCard
import dev.kadirkid.rickandmorty.app.singlescreen.CharacterCard
import dev.kadirkid.rickandmorty.design.CustomLazyColumn
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

@Composable
internal fun CharacterList(
    characters: LazyPagingItems<SimpleCharacter>,
    screenType: ScreenType,
    onCharacterClicked: (SimpleCharacter) -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomLazyColumn(modifier = modifier) { customModifier ->
        when (val loadState = characters.loadState.refresh) {
            is LoadStateLoading -> {
                item {
                    Row(Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
            }

            is LoadStateNotLoading -> {
                items(
                    count = characters.itemCount,
                    key = {
                        characters.itemKey {
                            it.id
                        }
                    },
                ) { index ->
                    characters[index]?.let { item ->
                        when (screenType) {
                            ScreenType.NONE -> {
                                CharacterCard(
                                    character = item,
                                    modifier = customModifier
                                        .padding(8.dp)
                                        .clickable { onCharacterClicked(item) },
                                )
                            }

                            ScreenType.SPLIT -> {
                                SimpleCard(
                                    character = item,
                                    modifier = customModifier
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                        .clickable { onCharacterClicked(item) },
                                )
                            }
                        }

                    }
                }
            }

            is LoadStateError -> {
                item {
                    Text(
                        text = loadState.error.message ?: "",
                        fontSize = 64.sp
                    )
                }
            }

            else -> {}
        }
    }
}