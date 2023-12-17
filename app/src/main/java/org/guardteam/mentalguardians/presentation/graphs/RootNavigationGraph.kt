package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.guardteam.mentalguardians.presentation.common.Graph
import org.guardteam.mentalguardians.presentation.main.MainScreen
import org.guardteam.mentalguardians.presentation.welcome.WelcomeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RootNavGraph(
    startDestination: String,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.WELCOME) {
            WelcomeScreen(
                navigateToSignIn = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.SignIn.route)
                }
            )
        }
        composable(route = Graph.MAIN) {
            MainScreen {
                navController.popBackStack()
                navController.navigate(Graph.AUTH)
            }
        }

        composable(route = Graph.BLANK) {}
    }
}