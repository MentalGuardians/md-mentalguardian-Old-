package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.TransactionDataDto
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