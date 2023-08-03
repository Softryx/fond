package opnutz.eu.fond.ui.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import opnutz.eu.fond.R
import opnutz.eu.fond.ui.theme.FondTheme

data class Input(val label: String, val type: Type) {
    enum class Type {
        TEXT,
        DOUBLE
    }
}

@Composable
fun InputDialog(
    title: String,
    inputs: List<Input>,
    onValidate: (List<Any>) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            color = FondTheme.colors.background,
            shape = RoundedCornerShape(20.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp, top = 12.dp)
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = title
                )

                val values = remember {
                    mutableStateListOf(
                        *(inputs.map {
                            TextFieldValue(
                                when (it.type) {
                                    Input.Type.TEXT -> ""
                                    Input.Type.DOUBLE -> "0.0"
                                }
                            )
                        }.toTypedArray())
                    )
                }


                inputs.forEachIndexed { index, input ->
                    FondTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        value = values[index],
                        onValueChange = {
                            values[index] = it
                        },
                        placeholder = input.label,
                        label = input.label,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = when (input.type) {
                                Input.Type.TEXT -> KeyboardType.Text
                                Input.Type.DOUBLE -> KeyboardType.Number
                            }
                        )
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    FondButton(
                        text = stringResource(id = R.string.cancel),
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    FondButton(
                        text = stringResource(id = R.string.validate),
                        onClick = {
                            onValidate(
                                values.mapIndexed { index, textFieldValue ->
                                    when (inputs[index].type) {
                                        Input.Type.TEXT -> textFieldValue.text
                                        Input.Type.DOUBLE -> textFieldValue.text.toDouble()
                                    }
                                }
                            )
                            onDismiss()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InputDialogSingleInputPreview() {
    FondTheme {
        InputDialog(
            title = stringResource(id = R.string.create_new_profile),
            inputs = listOf(
                Input(
                    label = stringResource(id = R.string.profile_name),
                    type = Input.Type.TEXT
                )
            ),
            onValidate = {},
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun InputDialogDoubleInputPreview() {
    FondTheme {
        InputDialog(
            title = stringResource(id = R.string.create_new_profile),
            inputs = listOf(
                Input(
                    label = stringResource(id = R.string.account_name),
                    type = Input.Type.TEXT
                ),
                Input(
                    label = stringResource(id = R.string.ceiling_in_euro),
                    type = Input.Type.DOUBLE
                )
            ),
            onValidate = {},
            onDismiss = {}
        )
    }
}