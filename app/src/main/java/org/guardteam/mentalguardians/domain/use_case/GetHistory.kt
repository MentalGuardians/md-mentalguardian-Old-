package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetHistory(
    private val repository: FeatureRepository
){
    operator fun invoke():Flow<Result<HistoryData>> {
        return repository.history()
    }
}