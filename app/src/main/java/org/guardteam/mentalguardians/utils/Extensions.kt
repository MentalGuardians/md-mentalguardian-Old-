package org.guardteam.mentalguardians.utils

import org.guardteam.mentalguardians.presentation.common.InputTextState

fun InputTextState.isInvalid(): Boolean {
    return this.value.isEmpty() || this.isError
}