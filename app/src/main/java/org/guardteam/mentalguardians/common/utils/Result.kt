package org.guardteam.mentalguardians.common.utils

sealed class Result<out T : Any?> {

    data object None : Result<Nothing>()
    data object Loading : Result<Nothing>()

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(val error: String) : Result<Nothing>()
}