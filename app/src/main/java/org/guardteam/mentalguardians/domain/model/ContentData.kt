package org.guardteam.mentalguardians.domain.model

data class ContentData(
    val contentId: String,
    val title: String,
    val author: String,
    val labels: String,
    val comments: Int,
    val likes: Int,
    val videoId: String,
    val views: Int,
    val thumbnail: String
)
