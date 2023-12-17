package org.guardteam.mentalguardians.presentation.profile.data

data class Profile(
    val id: String,
    val account: String,
    val username: String,
    val address: String? = null,
    val telephone: String? = null
)
