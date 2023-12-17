package org.guardteam.mentalguardians.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.guardteam.mentalguardians.presentation.common.BottomBarScreen
import org.guardteam.mentalguardians.presentation.graphs.MainNavGraph
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    goBackToAuth: () -> Unit = {}
) {

    var featuresTitle by rememberSaveable {
        mutableStateOf("")
    }

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
        BottomBarScreen.Transaction,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = featuresTitle,
                navController = navController,
                bottomBarDestination = bottomBarDestination
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                bottomBarDestination = bottomBarDestination,
                currentDestination = currentDestination,
                screens = screens
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            MainNavGraph(
                navController = navController,
                onFeaturesTitleChange = { title ->
                    featuresTitle = title
                }
            ) {
                navController.popBackStack()
                goBackToAuth()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navController: NavHostController,
    bottomBarDestination: Boolean,
    modifier: Modifier = Modifier
) {
    if (!bottomBarDestination) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                OutlinedIconButton(
                    onClick = { navController.navigateUp() },
                    border = BorderStroke(width = 1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarDestination: Boolean,
    currentDestination: NavDestination?,
    screens: List<BottomBarScreen>,
    modifier: Modifier = Modifier
) {
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
                            if (screen is BottomBarScreen.Home) {
                                navController.popBackStack()
                            }
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