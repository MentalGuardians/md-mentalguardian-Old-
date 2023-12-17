package org.guardteam.mentalguardians.presentation.predict

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.guardteam.mentalguardians.presentation.common.ChipState
import javax.inject.Inject

@HiltViewModel
class PredictViewModel @Inject constructor() : ViewModel() {

    private val _chipState = mutableStateListOf(
        ChipState(value = "Bullying"),
        ChipState(value = "Child"),
        ChipState(value = "Cognitive"),
        ChipState(value = "Educational, School"),
        ChipState(value = "Family"),
        ChipState(value = "Finance"),
        ChipState(value = "Love, Romance"),
        ChipState(value = "Nature-Nurture"),
        ChipState(value = "Parenting"),
        ChipState(value = "Friend"),
        ChipState(value = "Sexual")
    )
    val chipState: List<ChipState> = _chipState


    fun setChipSelectedAtIndex(index: Int, selected: Boolean) {
        _chipState[index] = _chipState[index].copy(selected = selected)
    }
}