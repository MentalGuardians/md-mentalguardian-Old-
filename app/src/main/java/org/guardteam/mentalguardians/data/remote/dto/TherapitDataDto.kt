package org.guardteam.mentalguardians.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TherapitDataDto(

    @field:SerializedName("therapistId")
    val therapistId: String,

    @field:SerializedName("Age")
    val age: Int,

    @field:SerializedName("Category")
    val category: String,

    @field:SerializedName("Domicile")
    val domicile: String,

    @field:SerializedName("Gender")
    val gender: String,

    @field:SerializedName("Methods")
    val method: String,

    @field:SerializedName("Name")
    val name: String,

    @field:SerializedName("Price")
    val price: String,

    @field:SerializedName("Rating")
    val rating: String,

    @field:SerializedName("Status")
    val status: String,

    @field:SerializedName("User Viewed")
    val viewed: Int
)
