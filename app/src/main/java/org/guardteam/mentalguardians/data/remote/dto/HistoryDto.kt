package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HistoryDto(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("historyData")
	val historyData: List<HistoryDataItem>,

	@field:SerializedName("status")
	val status: Int
)
