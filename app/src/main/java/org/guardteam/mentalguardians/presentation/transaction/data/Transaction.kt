package org.guardteam.mentalguardians.presentation.transaction.data

data class Transaction(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val status: String
)
