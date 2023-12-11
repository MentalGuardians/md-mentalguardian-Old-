package org.guardteam.mentalguardians.domain.use_case

import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class SaveOnBoarding(
    private val localDataManager: LocalDataManager
) {

    suspend operator fun invoke(complete: Boolean) {
        localDataManager.saveOnBoardingState(complete)
    }
}