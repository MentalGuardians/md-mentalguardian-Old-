package org.guardteam.mentalguardians.presentation.profile.extra

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {
    private val _editProfile: MutableStateFlow<Event<Result<Response>>> =
        MutableStateFlow(Event(Result.None))

    val editProfile: StateFlow<Event<Result<Response>>> = _editProfile

    fun editProfile(
        username: String,
        email: String,
        password: String,
        phone: String,
        address: String
    ) {
        viewModelScope.launch {
            featureUseCase.postEditProfile(
                username = username,
                email = email,
                password = password,
                phone = phone,
                alamat = address
            ).collect {
                _editProfile.value = Event(it)
            }
        }
    }
}