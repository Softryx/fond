package opnutz.eu.fond.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.colorResource
import opnutz.eu.fond.R

@Composable
fun FondTheme(content: @Composable () -> Unit) {
    val customColors = FondColors(
        primary = colorResource(id = R.color.primary),
        secondary = colorResource(id = R.color.secondary),
        background = colorResource(id = R.color.background),
        error = colorResource(id = R.color.error),
        black = colorResource(id = R.color.black),
        white = colorResource(id = R.color.white)
    )

    CompositionLocalProvider(
        LocalColors provides customColors,
        LocalTypography provides Typography,
        content = content
    )
}

object FondTheme {
    val colors: FondColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: FondTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}