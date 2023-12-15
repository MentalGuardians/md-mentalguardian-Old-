package org.guardteam.mentalguardians.domain.model

data class ContentById(

    val error: Boolean,

    val status: Int,

    val message: String,

    val data: ContentData
)
