package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Login
import org.guardteam.mentalguardians.domain.repository.AuthRepository

class PostLogin(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Result<Login>> {
        return authRepository.login(
            email = email,
            password = password
        )
    }
}