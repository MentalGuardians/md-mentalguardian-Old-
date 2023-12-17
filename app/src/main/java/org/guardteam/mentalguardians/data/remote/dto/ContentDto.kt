package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ContentDto(

    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("result")
    val result: List<ContentDataDto>
)
