package opnutz.eu.fond.ui.core

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import opnutz.eu.fond.ui.theme.FondTheme

@Composable
fun FondButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = FondTheme.colors.primary
        )
    ) {
        Text(text = text)
    }
}


@Preview
@Composable
private fun FondButtonPreview() {
    FondTheme {
        FondButton(
            text = "Button",
            onClick = {}
        )
    }
}