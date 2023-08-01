package opnutz.eu.fond.ui.core

import androidx.compose.foundation.shape.RoundedCornerShape
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
fun FondTextyField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = FondTheme.colors.black,
            cursorColor = FondTheme.colors.primary,
            focusedLabelColor = FondTheme.colors.primary,
            unfocusedLabelColor = FondTheme.colors.black,
            unfocusedBorderColor = FondTheme.colors.black,
            focusedBorderColor = FondTheme.colors.primary,
            backgroundColor = Color.Transparent
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
        }
    )
}


@Preview
@Composable
private fun FondTextyFieldPreview() {
    FondTheme {
        FondTextyField(
            value = TextFieldValue("Value"),
            onValueChange = {},
            label = "label",
            placeholder = "placeholder"
        )
    }
}