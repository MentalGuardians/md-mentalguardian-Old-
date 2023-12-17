package org.guardteam.mentalguardians.domain.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    val error: Boolean,
    val status: Int,
    val userId: String,
    val message: String,
    val historyBooking: List<TransactionData>
)
