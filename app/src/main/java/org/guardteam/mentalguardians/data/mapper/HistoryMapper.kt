package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.HistoryDataItem
import org.guardteam.mentalguardians.data.remote.dto.HistoryDto
import org.guardteam.mentalguardians.domain.model.History
import org.guardteam.mentalguardians.domain.model.HistoryData

fun HistoryDto.toHistoryData() : HistoryData {
    return HistoryData(
        error = error,
        message = message,
        userId = userId,
        historyData = historyData.map { it.toHistoryItem() },
        status = status
    )

}

fun HistoryDataItem.toHistoryItem() : History {
    return History(
        id = historyId,
        date = date,
        diagnose = text,
        mood = prediction
    )
}