package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.model.UserData

class GetUserData(
    private val localDataManager: LocalDataManager
) {
    operator fun invoke(): Flow<UserData> {
        return localDataManager.getUserData()
    }
}