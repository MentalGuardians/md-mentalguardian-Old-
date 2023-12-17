package org.guardteam.mentalguardians.presentation.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Result<Content>> =
        MutableStateFlow(Result.None)
    val result: StateFlow<Result<Content>> = _result

    fun getContent(content: String) {
        viewModelScope.launch {
            featureUseCase.getContent(content).collect {
                _result.value = it
            }
        }
    }
}