package org.guardteam.mentalguardians.domain.use_case

import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.model.UserData

class SaveUserData(
    private val localDataManager: LocalDataManager
) {
    suspend operator fun invoke(userData: UserData) {
        localDataManager.saveUserData(userData = userData)
    }
}