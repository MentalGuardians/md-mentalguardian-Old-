package org.guardteam.mentalguardians.common

sealed class FeaturesScreen(val route: String) {
    data object Content : FeaturesScreen(route = "content")
    data object ContentDetail : FeaturesScreen(route = "content_detail/{contentId}") {
        fun createRoute(contentId: Int) = "content_detail/$contentId"
    }

    data object ContentFavorite : FeaturesScreen(route = "content_favorite")
    data object Therapist : FeaturesScreen(route = "therapist")
    data object TherapistDetail : FeaturesScreen(route = "therapist_detail/{therapistId}") {
        fun createRoute(therapistId: Int) = "therapist_detail/$therapistId"
    }

    data object TherapistFavorite : FeaturesScreen(route = "therapist_favorite")
    data object TherapistAppointment : FeaturesScreen(route = "therapist_appointment")


    data object EditProfile : FeaturesScreen(route = "edit_profile")
    data object PartnerRegistration : FeaturesScreen(route = "partner_registration")
}