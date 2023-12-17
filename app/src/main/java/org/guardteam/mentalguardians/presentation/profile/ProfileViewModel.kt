package org.guardteam.mentalguardians.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import org.guardteam.mentalguardians.domain.use_case.UserDataUseCase
import org.guardteam.mentalguardians.domain.model.Profile
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase,
    private val userDataUseCase: UserDataUseCase
) : ViewModel() {
    private val _profile: MutableStateFlow<Result<Profile>> =
        MutableStateFlow(Result.None)
    val profile: StateFlow<Result<Profile>> = _profile

    private fun getProfile() {
        viewModelScope.launch {
            featureUseCase.getProfile().collect {
                _profile.value = it
            }
        }
    }

    fun clearUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            userDataUseCase.clearUserData()
        }
    }

    init {
        getProfile()
    }
}