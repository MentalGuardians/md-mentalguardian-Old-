package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ContentDataDto(
    @field:SerializedName("contentId")
    val contentId: String,

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Author")
    val author: String,

    @field:SerializedName("Labels")
    val labels: String,

    @field:SerializedName("Comments")
    val comments: Int,

    @field:SerializedName("Likes")
    val likes: Int,

    @field:SerializedName("Video ID")
    val videoId: String,

    @field:SerializedName("Views")
    val views: Int,

    @field:SerializedName("Thumbnail")
    val thumbnail: String
)
