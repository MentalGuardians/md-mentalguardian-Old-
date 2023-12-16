package org.guardteam.mentalguardians.domain.model

data class Therapist(
    val statusCode: Int,
    val message: String,
    val result: List<TherapistData>
)
