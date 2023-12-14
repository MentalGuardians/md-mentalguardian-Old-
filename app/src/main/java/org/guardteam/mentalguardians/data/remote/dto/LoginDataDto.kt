package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginDataDto(
    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("picture")
    val picture: String
)
