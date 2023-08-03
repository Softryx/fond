package opnutz.eu.fond.ui.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import opnutz.eu.fond.R
import opnutz.eu.fond.ui.theme.FondTheme


@Composable
fun FondTopAppBar(
    title: String,
    @DrawableRes actionIcon: Int? = null,
    onClickAction: (() -> Unit)? = null,
    onClickBack: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = title)
        },
        elevation = 0.dp,
        actions = {
            if (actionIcon != null && onClickAction != null) {
                IconButton(onClick = onClickAction) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = actionIcon),
                        contentDescription = null,
                        tint = FondTheme.colors.black
                    )
                }
            }
        },
        backgroundColor = FondTheme.colors.background,
        navigationIcon = onClickBack?.let {
            {
                IconButton(onClick = onClickBack) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = FondTheme.colors.black
                    )
                }
            }
        }
    )
}


@Preview
@Composable
private fun FondTopAppBarPreview() {
    FondTheme {
        FondTopAppBar(
            title = "Titre",
            actionIcon = R.drawable.ic_add,
            onClickAction = { },
            onClickBack = {}
        )
    }
}

@Preview
@Composable
private fun FondTopAppBarNoActionNoBackPreview() {
    FondTheme {
        FondTopAppBar(
            title = "Titre"
        )
    }
}