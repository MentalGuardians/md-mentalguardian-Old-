package org.guardteam.mentalguardians.domain.use_case

import org.guardteam.mentalguardians.domain.manager.LocalDataManager

class SaveDynamicColor(
    private val localDataManager: LocalDataManager
) {
    suspend operator fun invoke(state: Boolean) {
        localDataManager.setDynamicColorState(state)
    }
}