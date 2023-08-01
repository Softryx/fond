package opnutz.eu.fond.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class FondColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val black: Color,
    val white: Color
)

internal val LocalColors = staticCompositionLocalOf {
    FondColors(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        background = Color.Unspecified,
        black = Color.Unspecified,
        white = Color.Unspecified
    )
}