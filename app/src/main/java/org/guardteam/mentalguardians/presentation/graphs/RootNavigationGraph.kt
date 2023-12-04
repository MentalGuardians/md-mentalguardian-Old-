package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.main.MainScreen

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}