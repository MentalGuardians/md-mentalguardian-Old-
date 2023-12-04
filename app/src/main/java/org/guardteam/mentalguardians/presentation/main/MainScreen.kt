package org.guardteam.mentalguardians.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.guardteam.mentalguardians.common.BottomBarScreen
import org.guardteam.mentalguardians.presentation.graphs.MainNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {

        }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val screen = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
        BottomBarScreen.Transaction,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screen.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
    }
}