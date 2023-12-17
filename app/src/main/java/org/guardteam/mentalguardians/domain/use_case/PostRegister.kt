package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.AuthRepository

class PostRegister(
    private val authRepository: AuthRepository
) {
        operator fun invoke(name: String, email: String, password: String): Flow<Result<Response>> {
            return authRepository.register(
                name = name,
                email = email,
                password = password
            )
        }
}