package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetTherapist(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(expert: String): Flow<Result<Therapist>> {
        return featureRepository.expert(expert)
    }
}