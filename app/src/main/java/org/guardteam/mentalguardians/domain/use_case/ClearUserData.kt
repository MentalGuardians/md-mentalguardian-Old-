package org.guardteam.mentalguardians.domain.use_case

import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class ClearUserData(
    private val localDataManager: LocalDataManager
) {
    suspend operator fun invoke() {
        localDataManager.clearUserData()
    }
}