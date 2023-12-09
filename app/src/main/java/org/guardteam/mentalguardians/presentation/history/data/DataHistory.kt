package org.guardteam.mentalguardians.presentation.history.data

data class DataHistory(
    val id: Int,
    val date: String,
    val time: String,
    val diagnosa : String,
    val mood: Mood
)
