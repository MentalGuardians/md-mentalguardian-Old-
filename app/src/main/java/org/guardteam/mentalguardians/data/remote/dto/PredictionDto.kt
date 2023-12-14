package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PredictionDto(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("prediction")
    val prediction: String
)
