package org.guardteam.mentalguardians.domain.model

data class Therapist(
    val id: Int,
    val name: String,
    val primaryFocus: String,
    val rating: Double,
    val cost: String,
    val experience: Int,
    val client: Int,
    val session: Int,
    val secondaryFocus: List<String>,
    val about: String
)
