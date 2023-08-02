package opnutz.eu.fond.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import opnutz.eu.fond.navigation.BottomNavItem
import opnutz.eu.fond.navigation.FondNavHost
import opnutz.eu.fond.navigation.Route
import opnutz.eu.fond.ui.core.FondBottomNavBar
import opnutz.eu.fond.ui.theme.FondTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = BottomNavItem.all()

    if (currentDestination?.route == Route.HOME &&
        navController.previousBackStackEntry?.destination?.route == Route.LOGIN
    ) {
        BackHandler {
            viewModel.resetCurrentProfile()
            navController.popBackStack()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        backgroundColor = FondTheme.colors.background,
        bottomBar = {
            if (bottomNavItems.map { it.route }.contains(currentDestination?.route)) {
                FondBottomNavBar(items = bottomNavItems,
                    selectedItem = BottomNavItem.fromRoute(currentDestination?.route),
                    onClickItem = {
                        navController.navigate(it.route)
                    })
            }
        }) {
        FondNavHost(
            modifier = Modifier.padding(it), navController = navController
        )
    }
}