package org.guardteam.mentalguardians.domain.model


data class Response(
    val error: Boolean,
    val status: Int,
    val message: String
)
