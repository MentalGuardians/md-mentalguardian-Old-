package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransactionDto(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("historyBooking")
    val historyBooking: List<TransactionDataDto>
)
