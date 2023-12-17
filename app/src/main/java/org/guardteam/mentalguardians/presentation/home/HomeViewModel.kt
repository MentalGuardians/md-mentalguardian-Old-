package org.guardteam.mentalguardians.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.presentation.common.InputTextState
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.utils.isInvalid
import org.guardteam.mentalguardians.domain.model.Prediction
import org.guardteam.mentalguardians.domain.model.UserData
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import org.guardteam.mentalguardians.domain.use_case.UserDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase,
    private val userDataUseCase: UserDataUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Event<Result<Prediction>>> =
        MutableStateFlow(Event(Result.None))
    val result: StateFlow<Event<Result<Prediction>>> = _result

    private val _describeState: MutableState<InputTextState> = mutableStateOf(InputTextState())
    val describeState: State<InputTextState> = _describeState

    fun setDescribeState(inputTextState: InputTextState) {
        _describeState.value = inputTextState
    }

    private val _profile: MutableState<UserData> = mutableStateOf(UserData("", "", "", ""))
    val profile: State<UserData> = _profile

    init {
        viewModelScope.launch {
            userDataUseCase.getUserData().collect {
                _profile.value = it
            }
        }
    }

    fun getPredict() {
        when {
            _describeState.value.isInvalid() -> _describeState.value =
                _describeState.value.copy(isError = true)

            else -> {
                viewModelScope.launch {
                    featureUseCase.getPredict(_describeState.value.value)
                        .collect {
                            _result.value = Event(it)
                        }
                }
            }
        }
    }
}