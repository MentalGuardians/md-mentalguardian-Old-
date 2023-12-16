package org.guardteam.mentalguardians.domain.model

data class TherapistData(
    val therapistId: String,
    val age: Int,
    val category: String,
    val domicile: String,
    val gender: String,
    val method: String,
    val name: String,
    val price: String,
    val rating: String,
    val status: String,
    val viewed: Int
)
