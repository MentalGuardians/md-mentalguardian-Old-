package org.guardteam.mentalguardians.domain.model


data class Login(
    val error: Boolean,
    val status: Int,
    val message: String,
    val loginResult: UserData
)
