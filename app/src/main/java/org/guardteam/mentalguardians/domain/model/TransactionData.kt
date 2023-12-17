package org.guardteam.mentalguardians.domain.model

data class TransactionData(
    val bookingId: String,
    val bookingDate: String,
    val sessionDate: String,
    val sessionTime: String,
    val sessionMethod: String,
    val link: String? = null,
    val status: String,
    val therapistId: String,
    val therapistName: String
)
