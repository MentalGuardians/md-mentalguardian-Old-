package org.guardteam.mentalguardians.domain.model

data class Profile(
    val id: String,
    val account: String,
    val username: String,
    val address: String? = null,
    val telephone: String? = null
)
