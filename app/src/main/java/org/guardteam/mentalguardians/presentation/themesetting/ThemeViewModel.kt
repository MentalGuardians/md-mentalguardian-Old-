package org.guardteam.mentalguardians.presentation.themesetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.guardteam.mentalguardians.domain.use_case.ThemeUseCase
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {

    val dynamicColor: Flow<Boolean> = themeUseCase.getDynamicColor()

    val darkTheme: Flow<String> = themeUseCase.getDarkTheme()

    fun saveDynamicColor(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            themeUseCase.saveDynamicColor(state = state)
        }
    }

    fun saveDarkTheme(state: String) {
        viewModelScope.launch(Dispatchers.IO) {
            themeUseCase.saveDarkTheme(state)
        }
    }
}