package opnutz.eu.fond.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import opnutz.eu.fond.R
import opnutz.eu.fond.ui.theme.FondTheme

@Composable
fun LoginScreen(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(onClickCreateProfile: ()->Unit, accounts : List<String>, onClickAccount: (String)->Unit){
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(start = 28.dp, end = 28.dp, top = 28.dp, bottom = 80.dp), verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)){
            items(accounts){ account ->
                Card(onClick = {onClickAccount(account)}, modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(2.dp, Color.Black)) {
                    Text(text = account, modifier = Modifier.padding(12.dp))
                }
            }
        }
        Button(onClick = onClickCreateProfile, modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)) {
            Text(text = stringResource(id = R.string.create_profile))
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview(){
    FondTheme{
        LoginScreenContent(onClickCreateProfile = {}, listOf("Invité", "Quentin"), onClickAccount = {})
    }
}