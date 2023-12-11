package org.guardteam.mentalguardians

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.domain.use_case.OnBoardingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.BLANK)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            onBoardingUseCase.getOnBoarding().collect { complete ->
                if (complete) {
                    _startDestination.value = Graph.AUTH
                } else {
                    _startDestination.value = Graph.WELCOME
                }
            }
            _isLoading.value = false
        }
    }
}