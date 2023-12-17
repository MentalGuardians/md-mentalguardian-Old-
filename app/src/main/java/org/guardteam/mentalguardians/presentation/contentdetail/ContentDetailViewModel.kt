package org.guardteam.mentalguardians.presentation.contentdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.use_case.FeatureUseCase
import javax.inject.Inject

@HiltViewModel
class ContentDetailViewModel @Inject constructor(
    private val featureUseCase: FeatureUseCase
) : ViewModel() {

    private val _result: MutableStateFlow<Result<ContentById>> = MutableStateFlow(Result.None)
    val result: StateFlow<Result<ContentById>> = _result

    fun getContentById(contentId: String) {
        viewModelScope.launch {
            featureUseCase.getContentById(contentId).collect {
                _result.value = it
            }
        }
    }
}