package org.guardteam.mentalguardians.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalDataManager {

    suspend fun saveOnBoardingState(complete: Boolean)

    fun getOnBoardingState(): Flow<Boolean>
}