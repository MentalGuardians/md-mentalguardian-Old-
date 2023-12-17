package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.guardteam.mentalguardians.presentation.common.BottomBarScreen
import org.guardteam.mentalguardians.presentation.common.FeaturesScreen
import org.guardteam.mentalguardians.presentation.common.Graph
import org.guardteam.mentalguardians.presentation.history.HistoryScreen
import org.guardteam.mentalguardians.presentation.home.HomeScreen
import org.guardteam.mentalguardians.presentation.profile.ProfileScreen
import org.guardteam.mentalguardians.presentation.transaction.TransactionScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    onFeaturesTitleChange: (String) -> Unit,
    goBackToAuth: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToContent = {
                    navController.navigate(FeaturesScreen.Content.route)
                },
                navigateToTherapist = {
                    navController.navigate(FeaturesScreen.Therapist.route)
                },
                navigateToPredict = {
                    navController.navigate(FeaturesScreen.Prediction.createRoute(it))
                }
            )
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen()
        }
        composable(route = BottomBarScreen.Transaction.route) {
            TransactionScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                navigateToEdit = {
                    navController.navigate(FeaturesScreen.EditProfile.route)
                },
                navigateToTheme = {
                    navController.navigate(FeaturesScreen.ThemeSetting.route)
                },
                navigateToLogin = goBackToAuth,
                navigateToRegistration = {
                    navController.navigate(FeaturesScreen.PartnerRegistration.route)
                }
            )
        }

        featuresNavGraph(
            navController = navController,
            onFeaturesTitleChange = onFeaturesTitleChange
        )
    }
}