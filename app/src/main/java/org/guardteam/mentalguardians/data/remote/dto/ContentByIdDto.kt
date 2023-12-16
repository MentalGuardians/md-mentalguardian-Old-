package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ContentByIdDto(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: ContentDataDto
)
