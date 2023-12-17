package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class PostBooking(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(
        therapistId: String,
        date: String,
        time: String,
        method: String
    ): Flow<Result<Response>> {
        return featureRepository.booking(
            therapistId = therapistId,
            date = date,
            time = time,
            method = method
        )
    }
}