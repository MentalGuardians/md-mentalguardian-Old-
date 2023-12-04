package org.guardteam.mentalguardians.common

import androidx.annotation.DrawableRes
import org.guardteam.mentalguardians.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes
    val icon: Int
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home
    )

    data object History : BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.folder
    )

    data object Transaction : BottomBarScreen(
        route = "transaction",
        title = "Transaction",
        icon = R.drawable.transaction
    )

    data object Profile : BottomBarScreen(
        route = "profile",
        title = "Title",
        icon = R.drawable.profile
    )
}