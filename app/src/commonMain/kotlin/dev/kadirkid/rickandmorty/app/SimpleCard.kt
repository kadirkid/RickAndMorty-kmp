package dev.kadirkid.rickandmorty.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.kadirkid.rickandmorty.design.component.AsyncImage
import dev.kadirkid.rickandmorty.design.core.SizeToken
import dev.kadirkid.rickandmorty.design.core.TypographyToken
import dev.kadirkid.rickandmorty.design.core.color.LocalTextColors
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

@Composable
internal fun SimpleCard(character: SimpleCharacter, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            character.image?.let {
                AsyncImage(
                    url = it,
                    contentDescription = "Character Image",
                    size = SizeToken.LARGE,
                    modifier = Modifier.clip(CardDefaults.shape)
                )
            }
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                character.name?.let {
                    Text(
                        text = it,
                        color = LocalTextColors.current.primary.color,
                        fontSize = TypographyToken.Label.Medium.textStyle.fontSize
                    )
                }
                character.origin?.let {
                    Text(
                        text = it.name,
                        color = LocalTextColors.current.secondary.color,
                        fontSize = TypographyToken.Label.Medium.textStyle.fontSize,
                        modifier = Modifier.padding(top = 4.dp, end = 8.dp)
                    )
                }
            }
        }
    }
}