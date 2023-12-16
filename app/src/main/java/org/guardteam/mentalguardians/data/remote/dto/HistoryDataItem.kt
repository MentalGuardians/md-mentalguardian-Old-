package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HistoryDataItem(
    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("historyId")
    val historyId: String,

    @field:SerializedName("prediction")
    val prediction: String,

    @field:SerializedName("text")
    val text: String
)
