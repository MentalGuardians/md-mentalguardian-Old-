package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.TransactionDataDto
import org.guardteam.mentalguardians.data.remote.dto.TransactionDto
import org.guardteam.mentalguardians.domain.model.Transaction
import org.guardteam.mentalguardians.domain.model.TransactionData

fun TransactionDataDto.toTransactionData(): TransactionData {
    return TransactionData(
        bookingId = bookingId,
        bookingDate = bookingDate,
        sessionDate = sessionDate,
        sessionTime = sessionTime,
        sessionMethod = sessionMethod,
        link = link,
        status = status,
        therapistId = therapistId,
        therapistName = therapistName
    )
}

fun TransactionDto.toTransaction(): Transaction {
    return Transaction(
        error = error,
        status = status,
        userId = message,
        message = userId,
        historyBooking = historyBooking.map { it.toTransactionData() })
}