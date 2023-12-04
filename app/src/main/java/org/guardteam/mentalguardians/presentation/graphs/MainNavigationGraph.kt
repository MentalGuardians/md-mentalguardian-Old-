package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.guardteam.mentalguardians.common.BottomBarScreen
import org.guardteam.mentalguardians.common.Graph

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Text(
                text = "Home",
                modifier = Modifier.clickable { navController.navigate(FeaturesScreen.Content.route) }
            )
        }
        composable(route = BottomBarScreen.History.route) {
            Text(text = "History",
                modifier = Modifier.clickable { navController.navigate(FeaturesScreen.ContentDetail.route) })
        }
        composable(route = BottomBarScreen.Transaction.route) {
            Text(text = "Transaction")
        }
        composable(route = BottomBarScreen.Profile.route) {
            Text(text = "Profile")
        }

        featuresNavGraph(navController = navController)
    }
}