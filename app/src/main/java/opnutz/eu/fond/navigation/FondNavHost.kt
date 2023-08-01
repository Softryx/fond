package opnutz.eu.fond.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import opnutz.eu.fond.ui.login.LoginScreen


object Route {
    const val LOGIN = "login"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FondNavHost() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Route.LOGIN
    ) {
        composable(route = Route.LOGIN) {
            LoginScreen()
        }
    }
}