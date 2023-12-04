package org.guardteam.mentalguardians.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.guardteam.mentalguardians.common.BottomBarScreen
import org.guardteam.mentalguardians.presentation.graphs.MainNavGraph
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(navController = navController)
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
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
        BottomBarScreen.Transaction,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            modifier = modifier
        ) {
            screens.forEach { screen ->
                val selected = currentDestination?.route == screen.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = if (selected) screen.selectedIcon else screen.icon),
                            contentDescription = screen.title,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            fontFamily = fontFamily,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                )
            }
        }
    }
}