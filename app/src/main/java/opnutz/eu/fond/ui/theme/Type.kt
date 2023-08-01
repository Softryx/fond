package opnutz.eu.fond.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import javax.annotation.concurrent.Immutable

@Immutable
data class FondTypography(
    val h1: TextStyle
)

val Typography = FondTypography(
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf {
    FondTypography(
        h1 = TextStyle.Default
    )
}