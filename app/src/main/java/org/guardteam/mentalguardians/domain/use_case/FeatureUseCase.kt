package org.guardteam.mentalguardians.domain.use_case

data class FeatureUseCase(
    val getPredict: GetPredict,
    val getHistory: GetHistory,
    val getContent: GetContent,
    val getContentById: GetContentById,
    val getTherapist: GetTherapist,
    val getTherapistById: GetTherapistById,
    val postBooking: PostBooking,
    val getProfile: GetProfile
    val getTransaction: GetTransaction
)
