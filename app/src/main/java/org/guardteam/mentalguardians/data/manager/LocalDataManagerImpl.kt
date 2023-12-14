package org.guardteam.mentalguardians.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.guardteam.mentalguardians.common.utils.Contants.APP_PREF
import org.guardteam.mentalguardians.common.utils.Contants.EMAIL_KEY
import org.guardteam.mentalguardians.common.utils.Contants.LOGINSTATE_KEY
import org.guardteam.mentalguardians.common.utils.Contants.ONBOARDING_KEY
import org.guardteam.mentalguardians.common.utils.Contants.PICTURE_KEY
import org.guardteam.mentalguardians.common.utils.Contants.USERID_KEY
import org.guardteam.mentalguardians.common.utils.Contants.USERNAME_KEY
import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.model.UserData

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = APP_PREF)

class LocalDataManagerImpl(context: Context) : LocalDataManager {

    private val datastore = context.datastore

    override suspend fun saveOnBoardingState(complete: Boolean) {
        datastore.edit { pref ->
            pref[onBoardingKey] = complete
        }
    }

    override fun getOnBoardingState(): Flow<Boolean> {
        return datastore.data.map { pref ->
            pref[onBoardingKey] ?: false
        }
    }

    override suspend fun saveUserData(userData: UserData) {
        datastore.edit { pref ->
            pref[userIdKey] = userData.userId
            pref[usernameKey] = userData.username
            pref[emailKey] = userData.email
            pref[pictureKey] = userData.picture
            pref[loginStateKey] = true
        }
    }

    override fun getUserData(): Flow<UserData> {
        return datastore.data.map { pref ->
            UserData(
                userId = pref[userIdKey] ?: "",
                username = pref[usernameKey] ?: "",
                email = pref[emailKey] ?: "",
                picture = pref[pictureKey] ?: ""
            )
        }
    }

    override fun getLoginState(): Flow<Boolean> {
        return datastore.data.map { pref ->
            pref[loginStateKey] ?: false
        }
    }

    override suspend fun clearUserData() {
        datastore.edit { pref ->
            pref.remove(userIdKey)
            pref.remove(usernameKey)
            pref.remove(emailKey)
            pref.remove(pictureKey)
            pref.remove(loginStateKey)
        }
    }

    private companion object {
        val onBoardingKey = booleanPreferencesKey(name = ONBOARDING_KEY)
        val userIdKey = stringPreferencesKey(name = USERID_KEY)
        val usernameKey = stringPreferencesKey(name = USERNAME_KEY)
        val emailKey = stringPreferencesKey(name = EMAIL_KEY)
        val pictureKey = stringPreferencesKey(name = PICTURE_KEY)
        val loginStateKey = booleanPreferencesKey(name = LOGINSTATE_KEY)
    }
}