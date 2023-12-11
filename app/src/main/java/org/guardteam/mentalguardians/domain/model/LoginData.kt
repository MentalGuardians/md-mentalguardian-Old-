package org.guardteam.mentalguardians.domain.model


data class LoginData(
    val userId: String,
    val username: String,
    val email: String,
    val picture: String
)
