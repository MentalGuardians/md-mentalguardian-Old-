package org.guardteam.mentalguardians.domain.manager

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.domain.model.UserData

interface LocalDataManager {

    suspend fun saveOnBoardingState(complete: Boolean)

    fun getOnBoardingState(): Flow<Boolean>

    suspend fun saveUserData(userData: UserData)

    fun getUserData(): Flow<UserData>

    fun getLoginState(): Flow<Boolean>

    suspend fun clearUserData()

    fun getDynamicColorState(): Flow<Boolean>

    suspend fun setDynamicColorState(state: Boolean)

    fun getDarkModeState(): Flow<String>

    suspend fun setDarkModeState(state: String)
}