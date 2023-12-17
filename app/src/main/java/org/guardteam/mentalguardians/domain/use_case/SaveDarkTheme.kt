package org.guardteam.mentalguardians.domain.use_case

import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class SaveDarkTheme(
    private val localDataManager: LocalDataManager
) {
    suspend operator fun invoke(state: String) {
        localDataManager.setDarkModeState(state)
    }
}