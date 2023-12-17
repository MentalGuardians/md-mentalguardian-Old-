package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Transaction
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetTransaction(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(): Flow<Result<Transaction>> {
        return featureRepository.transaction()
    }
}