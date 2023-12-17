package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class PutCancelBooking(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(bookingId: String): Flow<Result<Response>> {
        return featureRepository.cancelBooking(bookingId)
    }
}