package opnutz.eu.fond.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import opnutz.eu.fond.R
import opnutz.eu.fond.data.vo.Profile
import opnutz.eu.fond.ui.core.FondButton
import opnutz.eu.fond.ui.core.FondTextyField
import opnutz.eu.fond.ui.theme.FondTheme

@Composable
fun LoginScreen(
    goToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val profiles by viewModel.profiles.collectAsState(initial = emptyList())
    val currentProfile by viewModel.currentProfile.collectAsState(initial = null)

    LaunchedEffect(currentProfile) {
        if (currentProfile != null) {
            goToHome()
        }
    }

    var showCreateUserDialog by remember {
        mutableStateOf(false)
    }

    LoginScreenContent(onClickCreateProfile = { showCreateUserDialog = true },
        profiles = profiles,
        onClickProfile = {
            viewModel.setCurrentProfile(it)
        }
    )

    if (showCreateUserDialog) {
        Dialog(
            onDismissRequest = { showCreateUserDialog = false }
        ) {
            CreateUserDialogContent(
                onClickValidate = {
                    viewModel.createUser(name = it)
                    showCreateUserDialog = false
                }, onClickCancel = {
                    showCreateUserDialog = false
                }
            )
        }
    }
}


@Composable
private fun LoginScreenContent(
    onClickCreateProfile: () -> Unit, profiles: List<Profile>, onClickProfile: (Profile) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 28.dp, end = 28.dp, top = 28.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            items(profiles.sortedBy { it.name }, key = { it.id }) { profile ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickProfile(profile)
                        },
                    backgroundColor = FondTheme.colors.white,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, FondTheme.colors.black)
                ) {
                    Text(text = profile.name, modifier = Modifier.padding(12.dp))
                }
            }
        }
        FondButton(
            text = stringResource(id = R.string.create_profile),
            onClick = onClickCreateProfile,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )
    }
}

@Composable
private fun CreateUserDialogContent(
    onClickValidate: (String) -> Unit, onClickCancel: () -> Unit
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
                text = stringResource(R.string.create_new_profile)
            )

            var username by remember {
                mutableStateOf(TextFieldValue(""))
            }

            FondTextyField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                value = username,
                onValueChange = {
                    username = it
                },
                placeholder = stringResource(id = R.string.profile_name),
                label = stringResource(id = R.string.profile_name)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                FondButton(
                    text = stringResource(id = R.string.cancel),
                    onClick = onClickCancel,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                FondButton(
                    text = stringResource(id = R.string.validate),
                    onClick = { onClickValidate(username.text) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    FondTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    FondTheme.colors.background
                )
        ) {
            LoginScreenContent(onClickCreateProfile = {}, listOf(
                Profile(
                    id = 0, name = "Invité"
                ), Profile(
                    id = 1, name = "Quentin"
                )
            ), onClickProfile = {})
        }
    }
}

@Preview
@Composable
private fun CreateUserDialogPreview() {
    FondTheme {
        CreateUserDialogContent(onClickValidate = {}, onClickCancel = {})
    }
}