package org.guardteam.mentalguardians.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.History
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
): ViewModel() {
    private val _history: MutableStateFlow<Result<HistoryData>> =
        MutableStateFlow(Result.None)
    val history : StateFlow<Result<HistoryData>> = _history

    private val _bottomSheetState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val bottomSheetState :StateFlow<Boolean> = _bottomSheetState

    private val _bottomSheetData: MutableStateFlow<History> =
        MutableStateFlow(
            History(
                id = "",
                date = "",
                diagnose = "",
                mood = ""
            )
        )
    val bottomSheetData : StateFlow<History> = _bottomSheetData

    fun setBottomSheetData(history: History){
        _bottomSheetData.value = history
    }

    fun getHistory(){
        viewModelScope.launch {
            featureUseCase.getHistory().collect{
                _history.value = it
            }
        }
    }

    fun onHistoryItemClicked(){
        _bottomSheetState.value = true
    }

    fun onDismissBottomSheet(){
        _bottomSheetState.value = false
    }

    init {
        getHistory()
    }
}