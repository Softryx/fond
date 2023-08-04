package opnutz.eu.fond.ui.accountdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import opnutz.eu.fond.R
import opnutz.eu.fond.data.vo.Account
import opnutz.eu.fond.data.vo.Operation
import opnutz.eu.fond.data.vo.relations.AccountWithOperations
import opnutz.eu.fond.ui.core.FondTopAppBar
import opnutz.eu.fond.ui.core.Input
import opnutz.eu.fond.ui.core.InputDialog
import opnutz.eu.fond.ui.theme.FondTheme


@Composable
fun AccountDetailScreen(
    accountId: Long, viewModel: AccountDetailViewModel = hiltViewModel()
) {
    val accountWithOperations by viewModel.account(accountId).collectAsState(initial = null)

    var showCreateOperationDialog by remember {
        mutableStateOf(false)
    }

    AccountDetailScreenContent(
        onClickCreateOperation = { showCreateOperationDialog = true },
        account = accountWithOperations
    )

    if (showCreateOperationDialog) {
        InputDialog(title = stringResource(id = R.string.create_new_operation), inputs = listOf(
            Input(
                label = stringResource(id = R.string.operation_name), type = Input.Type.TEXT
            ), Input(
                label = stringResource(id = R.string.amont_in_euro), type = Input.Type.DOUBLE
            ), Input(
                label = stringResource(id = R.string.date), type = Input.Type.TEXT
            )
        ), onValidate = {
            viewModel.createOperation(
                accountId = accountId,
                name = it[0] as String,
                amount = it[1] as Double,
                date = it[2] as String
            )
        }, onDismiss = {
            showCreateOperationDialog = false
        })
    }
}


@Composable
private fun AccountDetailScreenContent(
    onClickCreateOperation: () -> Unit, account: AccountWithOperations?
) {
    if (account == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = FondTheme.colors.primary
            )
        }
    } else {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            FondTopAppBar(
                title = account.account.name,
                actionIcon = R.drawable.ic_add,
                onClickAction = onClickCreateOperation
            )
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.money_format, account.accountAmount()
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.ceiling_format, stringResource(
                                id = R.string.money_format, account.account.ceiling
                            )
                        )
                    )
                }
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, start = 20.dp, end = 20.dp),
                    color = FondTheme.colors.primary,
                    progress = (account.accountAmount() / account.account.ceiling).coerceIn(
                        minimumValue = 0.0, maximumValue = 1.0
                    ).toFloat()
                )


                LazyColumn(
                    modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                        start = 20.dp, end = 20.dp, top = 28.dp, bottom = 28.dp
                    ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(account.operations) { operation ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = FondTheme.colors.white,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(2.dp, FondTheme.colors.black)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .padding(bottom = 4.dp), text = operation.name
                                    )
                                    Text(
                                        text = operation.date
                                    )
                                }

                                Text(
                                    modifier = Modifier.align(CenterVertically),
                                    text = stringResource(
                                        id = R.string.money_format, operation.amount
                                    )
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun AccountDetailScreenPreview() {
    FondTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    FondTheme.colors.background
                )
        ) {
            AccountDetailScreenContent(
                onClickCreateOperation = { }, account = AccountWithOperations(
                    account = Account(
                        id = 0, profileId = 0, ceiling = 1000.0, name = "Livret A"
                    ), operations = listOf(
                        Operation(
                            id = 0,
                            accountId = 0,
                            amount = -10.0,
                            date = "03/08/2023",
                            name = "Achat 1"
                        ), Operation(
                            id = 1,
                            accountId = 0,
                            amount = 3000.0,
                            date = "03/08/2023",
                            name = "Paie"
                        ), Operation(
                            id = 2,
                            accountId = 0,
                            amount = -20.0,
                            date = "03/08/2023",
                            name = "Achat 2"
                        ), Operation(
                            id = 3,
                            accountId = 0,
                            amount = -3.0,
                            date = "03/08/2023",
                            name = "Achat 3"
                        )
                    )
                )
            )

        }
    }
}