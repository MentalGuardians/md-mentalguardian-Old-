package org.guardteam.mentalguardians.presentation.therapist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject

@HiltViewModel
class TherapistViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Result<Therapist>> = MutableStateFlow(Result.None)
    val result: StateFlow<Result<Therapist>> = _result

    fun getTherapist(expert: String) {
        viewModelScope.launch {
            featureUseCase.getTherapist(expert).collect {
                _result.value = it
            }
        }
    }
}