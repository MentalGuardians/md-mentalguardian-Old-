package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransactionDataDto(

    @field:SerializedName("bookingId")
    val bookingId: String,

    @field:SerializedName("tanggal_booking")
    val bookingDate: String,

    @field:SerializedName("tanggal_konseling")
    val sessionDate: String,

    @field:SerializedName("jam_konseling")
    val sessionTime: String,

    @field:SerializedName("jenis_konseling")
    val sessionMethod: String,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("therapistId")
    val therapistId: String,

    @field:SerializedName("therapistName")
    val therapistName: String
)
