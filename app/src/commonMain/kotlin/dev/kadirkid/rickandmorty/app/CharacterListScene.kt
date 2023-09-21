package dev.kadirkid.rickandmorty.app

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kadirkid.rickandmorty.core.main.MainState
import dev.kadirkid.rickandmorty.core.main.MainViewModel
import dev.kadirkid.rickandmorty.design.component.AsyncImage
import dev.kadirkid.rickandmorty.design.core.SizeToken
import dev.kadirkid.rickandmorty.service.api.CharacterStatus
import dev.kadirkid.rickandmorty.service.api.SimpleCharacter

@Composable
internal fun CharacterListScene(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Button(
                onClick = { viewModel.reload() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) { Text(text = "Refresh") }
            Spacer(modifier = Modifier.size(8.dp))
            when (val state = viewModel.state.collectAsState().value) {
                is MainState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = state.message, fontSize = 64.sp)
                    }
                }

                is MainState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }

                is MainState.Success -> {
                    var largestWidth by remember { mutableStateOf(0) }
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        items(state.characters) {
                            CharacterCard(
                                character = it,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .layout { measurable, constraints ->
                                        val placeable = measurable.measure(constraints)
                                        if (placeable.width > largestWidth) largestWidth = placeable.width
                                        layout(largestWidth, placeable.height) {
                                            placeable.place(0, 0)
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun CharacterCard(character: SimpleCharacter, modifier: Modifier = Modifier) {
//    val movementSpec = SpringSpec<IntOffset>(
//        dampingRatio = Spring.DampingRatioMediumBouncy,
//        stiffness = 200f
//    )
//    var isTransformed by rememberSaveable { mutableStateOf(false) }
//    val size = animateSizeAsState(
//        targetValue = if (isTransformed) {
//            Size(width = 200f, height = 400f)
//        } else {
//            Size(width = 84f, height = 84f)
//        },
//        label = ""
//    )

//    Orbital(modifier = Modifier.clickable { isTransformed = !isTransformed }) {
//        if (isTransformed) {
//            Column(
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.Start
//            ) {
//                character.image?.ImageContent(
//                    modifier = Modifier.size(width = size.value.width.dp, height = size.value.height.dp)
//                )
//                character.InfoContent(showFullContent = isTransformed)
//            }
//        } else {
//            Card(modifier = modifier) {
//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.Start
//                ) {
//                    character.image?.ImageContent(
//                        modifier = Modifier.size(width = size.value.width.dp, height = size.value.height.dp)
//                    )
//                    character.InfoContent(showFullContent = isTransformed)
//                }
//            }
//        }
//    }

    Card(
        modifier = modifier
            .heightIn(max = 200.dp)
            .clickable { /* TODO */ }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
            .fillMaxHeight()
//            .heightIn(max = 140.dp)
//            .widthIn(max = 140.dp)
//            .aspectRatio(1f),
    )
//    CoilImage(
//        imageRequest = {
//            ImageRequest.Builder(context)
//                .data(this)
//                .lifecycle(lifecycleOwner)
//                .scale(Scale.FILL)
//                .build()
//        },
//        imageOptions = ImageOptions(
//            contentDescription = "Character Image",
//            contentScale = ContentScale.FillBounds
//        ),
//        modifier = modifier
//            .heightIn(max = 140.dp)
//            .widthIn(max = 140.dp)
//    )
}

@Composable
private fun SimpleCharacter.InfoContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.2f))
            .padding(16.dp)
    ) {
        name?.let { Text(text = it, fontSize = 24.sp) }
        status?.Content()
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = modifier) {
            drawCircle(color = color, radius = 4.dp.toPx())
        }
        Text(text = " - ")
        Text(text = name)
    }
}