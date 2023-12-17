package org.guardteam.mentalguardians.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.use_case.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Event<Result<Response>>> =
        MutableStateFlow(Event(Result.None))
    val result: StateFlow<Event<Result<Response>>> = _result

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            authUseCase.postRegister(name = name, email = email, password = password).collect {
                _result.value = Event(it)
            }
        }
    }
}