package org.guardteam.mentalguardians.domain.model

data class Content(
    val statusCode: Int,
    val message: String,
    val result: List<ContentData>
)
