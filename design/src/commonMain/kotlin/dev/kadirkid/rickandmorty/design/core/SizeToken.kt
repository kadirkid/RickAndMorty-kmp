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
package dev.kadirkid.rickandmorty.design.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SizeToken(val underlyingSize: Dp) {
    SMALL(24.dp),
    MEDIUM(32.dp),
    LARGE(48.dp),
    X_LARGE(64.dp),
    XX_LARGE(96.dp),
    XXX_LARGE(128.dp),
    XXXX_LARGE(192.dp),
    XXXXX_LARGE(256.dp),
    XXXXXX_LARGE(384.dp),
}
