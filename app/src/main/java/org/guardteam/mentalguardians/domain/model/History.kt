package org.guardteam.mentalguardians.domain.model

data class History(
    val id: String,
    val date: String,
    val diagnose: String,
    val mood: String
)
