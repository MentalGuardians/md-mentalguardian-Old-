package org.guardteam.mentalguardians.domain.repository

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Login
import org.guardteam.mentalguardians.domain.model.Response

interface AuthRepository {

    fun register(
        name: String,
        email: String,
        password: String
    ): Flow<Result<Response>>

    fun login(
        email: String,
        password: String
    ): Flow<Result<Login>>
}