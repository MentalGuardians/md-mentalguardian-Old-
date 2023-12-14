package org.guardteam.mentalguardians.domain.model

data class Prediction(
    val error: Boolean,
    val status: Int,
    val message: String,
    val prediction: String
)
