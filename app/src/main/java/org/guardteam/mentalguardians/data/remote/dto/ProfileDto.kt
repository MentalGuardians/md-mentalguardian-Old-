package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProfileDto(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
