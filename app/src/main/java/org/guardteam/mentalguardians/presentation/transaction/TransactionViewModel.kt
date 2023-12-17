package org.guardteam.mentalguardians.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Event
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.model.Transaction
import org.guardteam.mentalguardians.domain.model.TransactionData
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Result<Transaction>> = MutableStateFlow(Result.None)
    val result: StateFlow<Result<Transaction>> = _result

    private val _cancel: MutableStateFlow<Event<Result<Response>>> =
        MutableStateFlow(Event(Result.None))
    val cancel: StateFlow<Event<Result<Response>>> = _cancel

    private val _transactionData: MutableStateFlow<TransactionData> = MutableStateFlow(
        TransactionData(
            bookingId = "",
            bookingDate = "",
            sessionDate = "",
            sessionTime = "",
            sessionMethod = "",
            link = null,
            status = "",
            therapistId = "",
            therapistName = ""
        )
    )
    val transactionData: StateFlow<TransactionData> = _transactionData

    init {
        getTransaction()
    }

    fun getTransaction() {
        viewModelScope.launch {
            featureUseCase.getTransaction().collect {
                _result.value = it
            }
        }
    }

    fun setTransactionData(transactionData: TransactionData) {
        _transactionData.value = transactionData
    }

    fun cancelBooking(bookingId: String) {
        viewModelScope.launch {
            featureUseCase.putCancelBooking(bookingId).collect {
                _cancel.value = Event(it)
            }
        }
    }
}