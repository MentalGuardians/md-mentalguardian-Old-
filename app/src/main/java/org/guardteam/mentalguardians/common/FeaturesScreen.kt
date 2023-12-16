package org.guardteam.mentalguardians.common

sealed class FeaturesScreen(val route: String) {
    data object Content : FeaturesScreen(route = "content/{content}") {
        fun createRoute(content: String) = "content/$content"
    }

    data object ContentDetail : FeaturesScreen(route = "content_detail/{contentId}") {
        fun createRoute(contentId: String) = "content_detail/$contentId"
    }

    data object ContentFavorite : FeaturesScreen(route = "content_favorite")
    data object Therapist : FeaturesScreen(route = "therapist/{expert}") {
        fun createRoute(expert: String) = "therapist/$expert"
    }

    data object TherapistDetail : FeaturesScreen(route = "therapist_detail/{therapistId}") {
        fun createRoute(therapistId: String) = "therapist_detail/$therapistId"
    }

    data object TherapistFavorite : FeaturesScreen(route = "therapist_favorite")


    data object EditProfile : FeaturesScreen(route = "edit_profile")
    data object PartnerRegistration : FeaturesScreen(route = "partner_registration")
    data object TherapistAppointment :
        FeaturesScreen(route = "therapist_appointment/{therapistId}") {
        fun createRoute(therapistId: Int) = "therapist_appointment/$therapistId"
    }

    data object Prediction : FeaturesScreen(route = "prediction/{mood}") {
        fun createRoute(mood: String) = "prediction/$mood"
    }
}