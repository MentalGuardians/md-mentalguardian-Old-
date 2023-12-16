package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TherapistDto(

    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("result")
    val result: List<TherapitDataDto>
)
