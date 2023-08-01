package opnutz.eu.fond.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import opnutz.eu.fond.navigation.FondNavHost
import opnutz.eu.fond.ui.theme.FondTheme

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FondTheme.colors.background)
    ) {
        FondNavHost()
    }
}