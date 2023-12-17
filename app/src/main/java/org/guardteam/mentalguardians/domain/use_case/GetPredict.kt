package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Prediction
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetPredict(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(text: String): Flow<Result<Prediction>> {
        return featureRepository.predict(text)
    }
}