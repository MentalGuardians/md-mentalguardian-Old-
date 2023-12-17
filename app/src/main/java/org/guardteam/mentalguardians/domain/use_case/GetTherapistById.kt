package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.TherapistById
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetTherapistById(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(therapistId: String): Flow<Result<TherapistById>> {
        return featureRepository.expertById(therapistId)
    }
}