package opnutz.eu.fond.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import opnutz.eu.fond.ui.accountdetail.AccountDetailScreen
import opnutz.eu.fond.ui.accounts.AccountsScreen
import opnutz.eu.fond.ui.login.LoginScreen


object Route {
    const val LOGIN = "login"
    const val HOME = "home"
    const val ACCOUNTS = "accounts"
    const val OBJECTIVES = "objectives"
    const val SETTINGS = "settings"
    const val ACCOUNT_DETAIL = "accountDetail"
}

object PARAMETERS {
    const val ACCOUNT_ID = "accountId"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FondNavHost(
    navController: NavHostController, modifier: Modifier
) {

    AnimatedNavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Route.LOGIN
    ) {
        composable(route = Route.LOGIN) {
            LoginScreen(goToHome = {
                navController.navigate(Route.HOME)
            })
        }
        composable(route = Route.HOME) {}
        composable(route = Route.ACCOUNTS) {
            AccountsScreen(
                goToAccountDetail = {
                    navController.navigate("${Route.ACCOUNT_DETAIL}/$it")
                }
            )
        }
        composable(route = Route.OBJECTIVES) {}
        composable(route = Route.SETTINGS) {}
        composable(
            route = "${Route.ACCOUNT_DETAIL}/{${PARAMETERS.ACCOUNT_ID}}",
            arguments = listOf(
                navArgument(name = PARAMETERS.ACCOUNT_ID) {
                    type = NavType.LongType
                }
            )
        ) {
            AccountDetailScreen(
                accountId = it.arguments?.getLong(PARAMETERS.ACCOUNT_ID) ?: 0
            )
        }
    }
}