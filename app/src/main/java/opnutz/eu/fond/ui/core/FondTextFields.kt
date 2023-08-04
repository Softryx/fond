package opnutz.eu.fond.ui.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import opnutz.eu.fond.ui.theme.FondTheme

@Composable
fun FondTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    label: String? = null,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = FondTheme.colors.black,
                cursorColor = FondTheme.colors.primary,
                errorCursorColor = FondTheme.colors.primary,
                focusedLabelColor = FondTheme.colors.primary,
                unfocusedLabelColor = FondTheme.colors.black,
                unfocusedBorderColor = FondTheme.colors.black,
                focusedBorderColor = FondTheme.colors.primary,
                backgroundColor = Color.Transparent,
                errorBorderColor = FondTheme.colors.error,
                errorLabelColor = FondTheme.colors.error
            ),
            label = {
                label?.let {
                    Text(text = it)
                }
            },
            placeholder = {
                placeholder?.let {
                    Text(text = it)
                }
            },
            keyboardOptions = keyboardOptions,
            isError = error != null
        )
        error?.let {
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = error,
                color = FondTheme.colors.error
            )
        }
    }
}


@Preview
@Composable
private fun FondTextyFieldPreview() {
    FondTheme {
        FondTextField(
            value = TextFieldValue("Value"),
            onValueChange = {},
            label = "label",
            placeholder = "placeholder"
        )
    }
}

@Preview
@Composable
private fun FondTextyFieldErrorPreview() {
    FondTheme {
        FondTextField(
            value = TextFieldValue("Value"),
            onValueChange = {},
            label = "label",
            placeholder = "placeholder",
            error = "Erreur"
        )
    }
}