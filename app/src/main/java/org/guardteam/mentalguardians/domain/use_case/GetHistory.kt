package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetHistory(
    private val repository: FeatureRepository
){
    operator fun invoke(historyId : String):Flow<Result<HistoryData>> {
        return repository.history(historyId)
    }
}