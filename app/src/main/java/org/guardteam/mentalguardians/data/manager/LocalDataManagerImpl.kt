package org.guardteam.mentalguardians.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.guardteam.mentalguardians.common.utils.Contants.APP_PREF
import org.guardteam.mentalguardians.common.utils.Contants.ONBOARDING_KEY
import org.guardteam.mentalguardians.domain.manager.LocalDataManager

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

    private companion object {
        val onBoardingKey = booleanPreferencesKey(name = ONBOARDING_KEY)
    }
}