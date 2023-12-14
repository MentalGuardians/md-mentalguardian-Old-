package org.guardteam.mentalguardians.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.guardteam.mentalguardians.common.FeaturesScreen
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.book.BookScreen
import org.guardteam.mentalguardians.presentation.content.ContentScreen
import org.guardteam.mentalguardians.presentation.contentdetail.ContentDetailScreen
import org.guardteam.mentalguardians.presentation.contentfavorite.ContentFavoriteScreen
import org.guardteam.mentalguardians.presentation.predict.PredictScreen
import org.guardteam.mentalguardians.presentation.therapist.TherapistScreen
import org.guardteam.mentalguardians.presentation.therapistdetail.TherapistDetailScreen
import org.guardteam.mentalguardians.presentation.therapistfavorite.TherapistFavoriteScreen

fun NavGraphBuilder.featuresNavGraph(
    navController: NavHostController,
    onFeaturesTitleChange: (String) -> Unit,
    contentSearchActive: Boolean = false,
    onContentSearchActiveChange: (Boolean) -> Unit = {},
    therapistSearchActive: Boolean = false,
    onTherapistSearchActiveChange: (Boolean) -> Unit = {},
    goBackToAuth: () -> Unit = {}
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
            ContentFavoriteScreen(
                navigateToDetail = { contentId ->
                    navController.navigate(FeaturesScreen.ContentDetail.createRoute(contentId))
                }
            )
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
                onActiveChange = onTherapistSearchActiveChange,
                navigateToDetail = { therapistId ->
                    navController.navigate(FeaturesScreen.TherapistDetail.createRoute(therapistId))
                }
            )
            onFeaturesTitleChange("Therapist")
        }

        composable(route = FeaturesScreen.TherapistFavorite.route) {
            TherapistFavoriteScreen(
                navigateToDetail = { therapistId ->
                    navController.navigate(FeaturesScreen.TherapistDetail.createRoute(therapistId))
                }
            )
            onFeaturesTitleChange("Favorite Therapist")
        }

        composable(
            route = FeaturesScreen.TherapistDetail.route,
            arguments = listOf(navArgument("therapistId") { type = NavType.IntType })
        ) {
            val therapistId = it.arguments?.getInt("therapistId") ?: 1
            TherapistDetailScreen(
                therapistId = therapistId,
                navigateToBooking = { id ->
                    navController.navigate(FeaturesScreen.TherapistAppointment.createRoute(id))
                }
            )
            onFeaturesTitleChange("Detail Therapist")
        }

        composable(
            route = FeaturesScreen.TherapistAppointment.route,
            arguments = listOf(navArgument("therapistId") { type = NavType.IntType })
        ) {
            val therapistId = it.arguments?.getInt("therapistId") ?: 1
            BookScreen(therapistId = therapistId)
            onFeaturesTitleChange("Book Appointment")
        }

        composable(
            route = FeaturesScreen.Prediction.route,
            arguments = listOf(navArgument("mood") { type = NavType.StringType })
        ) {
            val mood = it.arguments?.getString("mood") ?: "good"
            PredictScreen(moodResult = mood)
            onFeaturesTitleChange("Prediction Result")
        }
    }
}
