package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.content.ContentScreen

fun NavGraphBuilder.featuresNavGraph(
    navController: NavHostController,
    onFeaturesTitleChange: (String) -> Unit,
    contentSearchActive: Boolean = false,
    onContentSearchActiveChange: (Boolean) -> Unit = {}
) {
    navigation(
        route = Graph.FEATURES,
        startDestination = FeaturesScreen.Content.route
    ) {
        composable(route = FeaturesScreen.Content.route) {
            ContentScreen(
                active = contentSearchActive,
                onActiveChange = onContentSearchActiveChange
            )
            onFeaturesTitleChange("Content")
        }
        composable(route = FeaturesScreen.ContentDetail.route) {
            Text(text = "Content Detail")
        }
    }
}

sealed class FeaturesScreen(val route: String) {
    data object Content : FeaturesScreen(route = "content")
    data object ContentDetail : FeaturesScreen(route = "content_detail")
    data object ContentFavorite : FeaturesScreen(route = "content_favorite")
    data object Therapist : FeaturesScreen(route = "therapist")
    data object TherapistDetail : FeaturesScreen(route = "therapist_detail")
    data object TherapistFavorite : FeaturesScreen(route = "therapist_favorite")
    data object TherapistAppointment : FeaturesScreen(route = "therapist_appointment")
}