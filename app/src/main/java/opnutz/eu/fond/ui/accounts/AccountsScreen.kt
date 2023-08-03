package opnutz.eu.fond.ui.accounts

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import opnutz.eu.fond.R
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.Operation
import opnutz.eu.fond.data.vo.Profile
import opnutz.eu.fond.data.vo.relations.AccountWithOperations
import opnutz.eu.fond.data.vo.relations.ProfileWithAccountsAndOperations
import opnutz.eu.fond.ui.core.FondTopAppBar
import opnutz.eu.fond.ui.core.Input
import opnutz.eu.fond.ui.core.InputDialog
import opnutz.eu.fond.ui.theme.FondTheme


@Composable
fun AccountsScreen(
    goToAccountDetail: (Long) -> Unit,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val profilesWithAccountsAndOperations by viewModel.currentProfile.collectAsState(initial = null)

    var showCreateAccountDialog by remember {
        mutableStateOf(false)
    }

    AccountsScreenContent(onClickCreateAccount = {
        showCreateAccountDialog = true
    },
        profile = profilesWithAccountsAndOperations,
        onClickAccount = {
            goToAccountDetail(it.id)
        }
    )

    if (showCreateAccountDialog) {
        InputDialog(title = stringResource(id = R.string.create_new_account),
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
            onValidate = {
                profilesWithAccountsAndOperations?.profile?.id?.let { profileId ->
                    viewModel.createAccount(
                        profileId = profileId,
                        name = it[0] as String,
                        ceiling = it[1] as Double
                    )
                }
            },
            onDismiss = {
                showCreateAccountDialog = false
            }
        )
    }
}


@Composable
private fun AccountsScreenContent(
    onClickCreateAccount: () -> Unit,
    profile: ProfileWithAccountsAndOperations?,
    onClickAccount: (Account) -> Unit
) {
    if (profile == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = FondTheme.colors.primary
            )
        }
    } else {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            FondTopAppBar(
                title = stringResource(id = R.string.money_format, profile.profilAmount()),
                actionIcon = R.drawable.ic_add,
                onClickAction = onClickCreateAccount
            )
        }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), contentPadding = PaddingValues(
                    start = 20.dp, end = 20.dp, top = 28.dp, bottom = 28.dp
                ), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(profile.accounts) { account ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onClickAccount(account.account)
                            },
                        backgroundColor = FondTheme.colors.white,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(2.dp, FondTheme.colors.black)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = account.account.name)
                                Text(
                                    text = stringResource(
                                        id = R.string.money_format, account.accountAmount()
                                    )
                                )
                            }
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 2.dp),
                                color = FondTheme.colors.primary,
                                progress = (account.accountAmount() / account.account.ceiling)
                                    .coerceIn(minimumValue = 0.0, maximumValue = 1.0).toFloat()
                            )

                            Text(
                                modifier = Modifier.align(Alignment.End),
                                text = stringResource(
                                    id = R.string.ceiling_format,
                                    stringResource(
                                        id = R.string.money_format,
                                        account.account.ceiling
                                    )
                                )
                            )

                        }
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun AccountsScreenPreview() {
    FondTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    FondTheme.colors.background
                )
        ) {
            AccountsScreenContent(
                onClickCreateAccount = { },
                profile = ProfileWithAccountsAndOperations(
                    profile = Profile(id = 0, name = "Invité"), accounts = listOf(
                        AccountWithOperations(
                            account = Account(
                                id = 0, profileId = 0, name = "Compte Courant", ceiling = 1000.0
                            ), operations = listOf(
                                Operation(id = 0, accountId = 0, 100.0, "2023-08-03"),
                                Operation(id = 1, accountId = 0, -20.0, "2023-08-03"),
                                Operation(id = 2, accountId = 0, 50.0, "2023-08-03"),
                            )
                        ), AccountWithOperations(
                            account = Account(
                                id = 1, profileId = 0, name = "Livret A", ceiling = 3000.0
                            ), operations = listOf(
                                Operation(id = 3, accountId = 1, 200.0, "2023-08-03"),
                                Operation(id = 4, accountId = 1, -50.0, "2023-08-03"),
                                Operation(id = 5, accountId = 1, 30.0, "2023-08-03"),
                            )
                        )
                    )
                ),
                onClickAccount = {}
            )
        }
    }
}