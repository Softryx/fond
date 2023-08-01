package opnutz.eu.fond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import opnutz.eu.fond.ui.MainScreen
import opnutz.eu.fond.ui.theme.FondTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FondTheme {
                MainScreen()
            }
        }
    }
}