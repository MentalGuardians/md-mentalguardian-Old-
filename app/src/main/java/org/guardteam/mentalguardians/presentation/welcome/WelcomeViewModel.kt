package org.guardteam.mentalguardians.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.domain.use_case.OnBoardingUseCase
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {
    fun saveOnBoardingState(complete: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingUseCase.saveOnBoarding(complete)
        }
    }
}