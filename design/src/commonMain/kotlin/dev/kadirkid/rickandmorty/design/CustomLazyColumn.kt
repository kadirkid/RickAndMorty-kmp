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
package dev.kadirkid.rickandmorty.design

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

@Composable
public fun CustomLazyColumn(
    modifier: Modifier = Modifier,
    content: LazyListScope.(Modifier) -> Unit,
) {
    var largestWidth by remember { mutableStateOf(0) }
    LazyColumn(modifier = modifier.padding(vertical = 8.dp)) {
        content(
            Modifier.layout { measurable, constraints ->
                val oldPlaceable = measurable.measure(constraints)
                if (oldPlaceable.width > largestWidth) largestWidth = oldPlaceable.width

                val newPlaceable = measurable.measure(
                    constraints.copy(
                        minWidth = largestWidth,
                        maxWidth = largestWidth,
                    ),
                )
                layout(newPlaceable.width, newPlaceable.height) {
                    newPlaceable.place(0, 0)
                }
            },
        )
    }
}
