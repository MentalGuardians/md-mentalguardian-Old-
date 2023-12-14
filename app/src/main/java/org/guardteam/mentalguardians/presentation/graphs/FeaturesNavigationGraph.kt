package org.guardteam.mentalguardians.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.guardteam.mentalguardians.common.FeaturesScreen
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.content.ContentScreen
import org.guardteam.mentalguardians.presentation.contentdetail.ContentDetailScreen
import org.guardteam.mentalguardians.presentation.contentfavorite.ContentFavoriteScreen
import org.guardteam.mentalguardians.presentation.profile.extra.EditProfile
import org.guardteam.mentalguardians.presentation.profile.extra.PartnerRegistration
import org.guardteam.mentalguardians.presentation.therapist.TherapistScreen
import org.guardteam.mentalguardians.presentation.therapistfavorite.TherapistFavoriteScreen

fun NavGraphBuilder.featuresNavGraph(
    navController: NavHostController,
    onFeaturesTitleChange: (String) -> Unit,
    contentSearchActive: Boolean = false,
    onContentSearchActiveChange: (Boolean) -> Unit = {},
    therapistSearchActive: Boolean = false,
    onTherapistSearchActiveChange: (Boolean) -> Unit = {}
) {
    navigation(
        route = Graph.FEATURES,
        startDestination = FeaturesScreen.Content.route
    ) {
        composable(route = FeaturesScreen.Content.route) {
            ContentScreen(
                active = contentSearchActive,
                onActiveChange = onContentSearchActiveChange,
                navigateToDetail = { contentId ->
                    navController.navigate(FeaturesScreen.ContentDetail.createRoute(contentId))
                }
            )
            onFeaturesTitleChange("Content")
        }
        composable(route = FeaturesScreen.ContentFavorite.route) {
            ContentFavoriteScreen()
            onFeaturesTitleChange("Favorite Content")
        }
        composable(
            route = FeaturesScreen.ContentDetail.route,
            arguments = listOf(navArgument("contentId") { type = NavType.IntType })
        ) {
            val contentId = it.arguments?.getInt("contentId") ?: 1
            ContentDetailScreen(contentId = contentId)
            onFeaturesTitleChange("Detail Content")
        }
        composable(route = FeaturesScreen.Therapist.route) {
            TherapistScreen(
                active = therapistSearchActive,
                onActiveChange = onTherapistSearchActiveChange
            )
            onFeaturesTitleChange("Therapist")
        }

        composable(route = FeaturesScreen.TherapistFavorite.route) {
            TherapistFavoriteScreen()
            onFeaturesTitleChange("Favorite Therapist")
        }

        composable(route = FeaturesScreen.EditProfile.route){
            EditProfile()
            onFeaturesTitleChange("Edit Profile")
        }

        composable(route = FeaturesScreen.PartnerRegistration.route){
            PartnerRegistration()
            onFeaturesTitleChange("Partner Registration")
        }
    }
}
