package opnutz.eu.fond.navigation

import androidx.annotation.DrawableRes
import opnutz.eu.fond.R


sealed class BottomNavItem(@DrawableRes var icon: Int, var route: String) {
    object Home : BottomNavItem(R.drawable.ic_home, Route.HOME)
    object Accounts : BottomNavItem(R.drawable.ic_accounts, Route.ACCOUNTS)
    object Objectives : BottomNavItem(R.drawable.ic_objectives, Route.OBJECTIVES)
    object Settings : BottomNavItem(R.drawable.ic_settings, Route.SETTINGS)

    companion object {
        fun fromRoute(route: String?): BottomNavItem? = all().find { it.route == route }

        fun all(): List<BottomNavItem> = listOf(Home, Accounts, Objectives, Settings)
    }
}