package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class GetDarkTheme(
    private val localDataManager: LocalDataManager
) {
    operator fun invoke(): Flow<String> {
        return localDataManager.getDarkModeState()
    }
}