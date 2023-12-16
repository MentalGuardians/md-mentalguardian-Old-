package org.guardteam.mentalguardians.presentation.transaction.data

data class Transaction(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val status: String,
    val description :String,
    val link : String,
    val whatsapp: String
)
