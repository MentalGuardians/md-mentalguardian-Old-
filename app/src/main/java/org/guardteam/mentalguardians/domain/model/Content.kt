package org.guardteam.mentalguardians.domain.model

data class Content(
    val id: Int,
    val title: String,
    val author: String,
    val duration: String,
    val rating: Double,
    val views: String,
    val desc: String
)
