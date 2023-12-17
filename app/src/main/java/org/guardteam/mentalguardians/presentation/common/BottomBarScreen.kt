package org.guardteam.mentalguardians.presentation.common

import androidx.annotation.DrawableRes
import org.guardteam.mentalguardians.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes
    val icon: Int,
    @DrawableRes
    val selectedIcon: Int
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home,
        selectedIcon = R.drawable.home_fill
    )

    data object History : BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.history,
        selectedIcon = R.drawable.history_fill
    )

    data object Transaction : BottomBarScreen(
        route = "transaction",
        title = "Transaction",
        icon = R.drawable.transaction,
        selectedIcon = R.drawable.transaction_fill
    )

    data object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile,
        selectedIcon = R.drawable.profile_fill
    )
}