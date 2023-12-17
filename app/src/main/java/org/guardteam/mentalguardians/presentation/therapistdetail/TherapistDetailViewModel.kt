package org.guardteam.mentalguardians.presentation.therapistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.model.TherapistById
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject

@HiltViewModel
class TherapistDetailViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Result<TherapistById>> = MutableStateFlow(Result.None)
    val result: StateFlow<Result<TherapistById>> = _result

    private val _booking: MutableStateFlow<Event<Result<Response>>> =
        MutableStateFlow(Event(Result.None))
    val booking: StateFlow<Event<Result<Response>>> = _booking

    fun getTherapistById(therapistId: String) {
        viewModelScope.launch {
            featureUseCase.getTherapistById(therapistId).collect {
                _result.value = it
            }
        }
    }

    fun postBooking(
        therapistId: String,
        date: String,
        time: String,
        method: String
    ) {
        viewModelScope.launch {
            featureUseCase.postBooking(therapistId, date, time, method).collect {
                _booking.value = Event(it)
            }
        }
    }
}