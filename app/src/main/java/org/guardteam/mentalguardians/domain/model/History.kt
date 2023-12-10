package org.guardteam.mentalguardians.domain.model

data class History(
    val id: Int,
    val date: String,
    val time: String,
    val diagnose : String
)
