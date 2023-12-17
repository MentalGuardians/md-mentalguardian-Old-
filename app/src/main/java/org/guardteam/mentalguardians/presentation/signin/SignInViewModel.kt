package org.guardteam.mentalguardians.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Login
import org.guardteam.mentalguardians.domain.model.UserData
import org.guardteam.mentalguardians.domain.use_case.AuthUseCase
import org.guardteam.mentalguardians.domain.use_case.UserDataUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val userDataUseCase: UserDataUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Event<Result<Login>>> =
        MutableStateFlow(Event(Result.None))
    val result: StateFlow<Event<Result<Login>>> = _result

    private val _isLogin: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLogin: StateFlow<Boolean> = _isLogin

    init {
        getLoginState()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.postLogin(email = email, password = password).collect {
                _result.value = Event(it)
            }
        }
    }

    fun saveUseData(userData: UserData) {
        viewModelScope.launch {
            userDataUseCase.saveUserData(userData = userData)
        }

        getLoginState()
    }

    private fun getLoginState() {
        viewModelScope.launch {
            userDataUseCase.getLoginState().collect {
                _isLogin.value = it
            }
        }
    }
}