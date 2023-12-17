package org.guardteam.mentalguardians.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.guardteam.mentalguardians.common.BottomBarScreen
import org.guardteam.mentalguardians.common.FeaturesScreen
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.book.BookScreen
import org.guardteam.mentalguardians.presentation.content.ContentScreen
import org.guardteam.mentalguardians.presentation.contentdetail.ContentDetailScreen
import org.guardteam.mentalguardians.presentation.predict.PredictScreen
import org.guardteam.mentalguardians.presentation.profile.extra.EditProfile
import org.guardteam.mentalguardians.presentation.profile.extra.PartnerRegistration
import org.guardteam.mentalguardians.presentation.themesetting.ThemeSettingScreen
import org.guardteam.mentalguardians.presentation.therapist.TherapistScreen
import org.guardteam.mentalguardians.presentation.therapistdetail.TherapistDetailScreen

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
        composable(
            route = FeaturesScreen.Content.route,
            arguments = listOf(navArgument("content") { type = NavType.StringType })
        ) {
            val content = it.arguments?.getString("content") ?: ""
            ContentScreen(
                active = contentSearchActive,
                onActiveChange = onContentSearchActiveChange,
                navigateToDetail = { contentId ->
                    navController.navigate(FeaturesScreen.ContentDetail.createRoute(contentId))
                },
                content = content
            )
            onFeaturesTitleChange("Content")
        }
        composable(
            route = FeaturesScreen.ContentDetail.route,
            arguments = listOf(navArgument("contentId") { type = NavType.StringType })
        ) {
            val contentId = it.arguments?.getString("contentId") ?: ""
            ContentDetailScreen(contentId = contentId)
            onFeaturesTitleChange("Detail Content")
        }
        composable(
            route = FeaturesScreen.Therapist.route,
            arguments = listOf(navArgument("expert") { NavType.StringType })
        ) {

            val expert = it.arguments?.getString("expert") ?: ""
            TherapistScreen(
                navigateToDetail = { therapistId ->
                    navController.navigate(FeaturesScreen.TherapistDetail.createRoute(therapistId))
                },
                expert = expert
            )
            onFeaturesTitleChange("Therapist")
        }
        composable(route = FeaturesScreen.EditProfile.route) {
            EditProfile()
            onFeaturesTitleChange("Edit Profile")
        }

        composable(route = FeaturesScreen.PartnerRegistration.route) {
            PartnerRegistration()
            onFeaturesTitleChange("Partner Registration")
        }
        composable(
            route = FeaturesScreen.TherapistDetail.route,
            arguments = listOf(navArgument("therapistId") { type = NavType.StringType })
        ) {
            val therapistId = it.arguments?.getString("therapistId") ?: ""
            TherapistDetailScreen(
                therapistId = therapistId,
                navigateToTransaction = {
                    navController.popBackStack(BottomBarScreen.Home.route, false)
                    navController.navigate(BottomBarScreen.Transaction.route)
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
        ) { it ->
            val mood = it.arguments?.getString("mood") ?: "good"
            PredictScreen(
                moodResult = mood,
                navigateToContent = {
                    navController.popBackStack(BottomBarScreen.Home.route, false)
                    navController.navigate(
                        if (it.isEmpty()) {
                            FeaturesScreen.Content.route
                        } else {
                            FeaturesScreen.Content.createRoute(it)
                        }
                    )
                },
                navigateToTherapist = {
                    navController.popBackStack(BottomBarScreen.Home.route, false)
                    navController.navigate(
                        if (it.isEmpty()) {
                            FeaturesScreen.Therapist.route
                        } else {
                            FeaturesScreen.Therapist.createRoute(it)
                        }
                    )
                }
            )
            onFeaturesTitleChange("Prediction Result")
        }

        composable(route = FeaturesScreen.ThemeSetting.route) {
            ThemeSettingScreen()
            onFeaturesTitleChange("Theme Setting")
        }
    }
}

