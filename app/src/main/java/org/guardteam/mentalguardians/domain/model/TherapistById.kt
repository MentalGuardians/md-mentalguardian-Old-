package org.guardteam.mentalguardians.domain.model

data class TherapistById(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: TherapistData
)
