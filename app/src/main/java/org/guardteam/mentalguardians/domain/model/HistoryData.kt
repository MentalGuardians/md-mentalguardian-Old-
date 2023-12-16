package org.guardteam.mentalguardians.domain.model

import org.guardteam.mentalguardians.data.remote.dto.HistoryDataItem

data class HistoryData(
    val error: Boolean,
    val message: String,
    val userId: String,
    val historyData: List<History>,
    val status: Int
)
