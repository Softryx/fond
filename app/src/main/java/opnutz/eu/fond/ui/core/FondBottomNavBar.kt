package opnutz.eu.fond.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import opnutz.eu.fond.navigation.BottomNavItem
import opnutz.eu.fond.ui.theme.FondTheme


@Composable
fun FondBottomNavBar(
    items: List<BottomNavItem>,
    selectedItem: BottomNavItem?,
    onClickItem: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = FondTheme.colors.primary,
        contentColor = FondTheme.colors.white,
        elevation = 0.dp
    ) {
        items.forEach {
            BottomNavigationItem(
                selected = it == selectedItem,
                onClick = { onClickItem(it) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                },
                selectedContentColor = FondTheme.colors.white,
                unselectedContentColor = FondTheme.colors.secondary
            )
        }
    }
}


@Preview
@Composable
private fun FondBottomNavBarPreview() {
    FondTheme {
        FondBottomNavBar(
            items = listOf(
                BottomNavItem.Home,
                BottomNavItem.Accounts,
                BottomNavItem.Objectives,
                BottomNavItem.Settings
            ),
            onClickItem = {},
            selectedItem = BottomNavItem.Home
        )
    }
}