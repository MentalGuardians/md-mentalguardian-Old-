package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class GetOnBoarding(
    private val localDataManager: LocalDataManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localDataManager.getOnBoardingState()
    }
}