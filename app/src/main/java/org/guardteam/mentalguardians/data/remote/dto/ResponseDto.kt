package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String
)
