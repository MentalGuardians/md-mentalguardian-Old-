package org.guardteam.mentalguardians.common.utils

import org.guardteam.mentalguardians.common.state.InputTextState

fun InputTextState.isInvalid(): Boolean {
    return this.value.isEmpty() || this.isError
}