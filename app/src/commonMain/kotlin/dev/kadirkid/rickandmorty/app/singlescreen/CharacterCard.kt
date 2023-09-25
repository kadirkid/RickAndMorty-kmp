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
package dev.kadirkid.rickandmorty.app.singlescreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kadirkid.rickandmorty.design.component.AsyncImage
import dev.kadirkid.rickandmorty.design.core.SizeToken
import dev.kadirkid.rickandmorty.service.api.CharacterStatus
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

@Composable
internal fun CharacterCard(character: SimpleCharacter, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.heightIn(max = 200.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            character.image?.ImageContent()
            character.InfoContent()
        }
    }
}

@Composable
private fun String.ImageContent(modifier: Modifier = Modifier) {
    AsyncImage(
        url = this,
        contentDescription = "Character Image",
        size = SizeToken.X_LARGE,
        modifier = modifier
            .width(170.dp)
            .fillMaxHeight(),
    )
}

@Composable
private fun SimpleCharacter.InfoContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.2f))
            .padding(16.dp),
    ) {
        Text(text = name, fontSize = 24.sp)
        status.Content()
        origin?.let {
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.DarkGray)) {
                    append("Place of origin: ")
                }
                append(it.name)
            }
            Text(text = text, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
private fun CharacterStatus.Content(modifier: Modifier = Modifier) {
    val color = when (this) {
        CharacterStatus.ALIVE -> Color.Green
        CharacterStatus.DEAD -> Color.Red
        CharacterStatus.UNKNOWN -> Color.Yellow
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(modifier = modifier) {
            drawCircle(color = color, radius = 4.dp.toPx())
        }
        Text(text = " - ")
        Text(text = name)
    }
}
