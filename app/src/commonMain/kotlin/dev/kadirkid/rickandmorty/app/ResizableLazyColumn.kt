package dev.kadirkid.rickandmorty.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize

@Composable
internal fun ResizeWidthColumn(modifier: Modifier, resize: Boolean, mainContent: @Composable () -> Unit) {
    SubcomposeLayout(modifier) { constraints ->
        val mainPlaceables = subcompose(SlotsEnum.Main, mainContent).map {
            // Here we measure the width/height of the child Composables
            it.measure(Constraints())
        }

        //Here we find the max width/height of the child Composables
        val maxSize = mainPlaceables.fold(IntSize.Zero) { currentMax, placeable ->
            IntSize(
                width = maxOf(currentMax.width, placeable.width),
                height = maxOf(currentMax.height, placeable.height)
            )
        }

        val resizedPlaceables: List<Placeable> =
            subcompose(SlotsEnum.Dependent, mainContent).map {
                if (resize) {
                    /** Here we rewrite the child Composables to have the width of
                     * widest Composable
                     */
                    it.measure(
                        Constraints(
                            minWidth = maxSize.width
                        )
                    )
                } else {
                    // Ask the child for its preferred size.
                    it.measure(Constraints())
                }
            }

        /**
         * We can place the Composables on the screen
         * with layout() and the place() functions
         */

        layout(constraints.maxWidth, constraints.maxHeight) {
            resizedPlaceables.forEachIndexed { index, placeable ->
                val widthStart = resizedPlaceables.take(index).sumOf { it.measuredHeight }
                placeable.place(0, widthStart)
            }
        }
    }
}

internal enum class SlotsEnum {
    Main,
    Dependent
}