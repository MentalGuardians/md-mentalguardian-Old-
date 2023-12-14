package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.guardteam.mentalguardians.common.BottomBarScreen
import org.guardteam.mentalguardians.common.FeaturesScreen
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.history.HistoryScreen
import org.guardteam.mentalguardians.presentation.home.HomeScreen
import org.guardteam.mentalguardians.presentation.profile.ProfileScreen
import org.guardteam.mentalguardians.presentation.transaction.TransactionScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    onFeaturesTitleChange: (String) -> Unit,
    contentSearchActive: Boolean = false,
    onContentSearchActiveChange: (Boolean) -> Unit = {},
    therapistSearchActive: Boolean = false,
    onTherapistSearchActiveChange: (Boolean) -> Unit = {},
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
                navigateToContent = {
                    navController.navigate(FeaturesScreen.ContentFavorite.route)
                },
                navigateToTherapist = {
                    navController.navigate(FeaturesScreen.TherapistFavorite.route)
                },
                navigateToRegistration = {
                    navController.navigate(FeaturesScreen.PartnerRegistration.route)
                }
            )
        }

        featuresNavGraph(
            navController = navController,
            onFeaturesTitleChange = onFeaturesTitleChange,
            contentSearchActive = contentSearchActive,
            onContentSearchActiveChange = onContentSearchActiveChange,
            therapistSearchActive = therapistSearchActive,
            onTherapistSearchActiveChange = onTherapistSearchActiveChange,
            goBackToAuth = goBackToAuth
        )
    }
}