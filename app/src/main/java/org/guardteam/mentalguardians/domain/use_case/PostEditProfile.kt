package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class PostEditProfile(
    private val repository: FeatureRepository
) {
    operator fun invoke(
        username: String,
        email: String,
        password: String,
        phone: String,
        alamat: String
    ): Flow<Result<Response>> {
        return repository.editProfile(
            username = username,
            password = password,
            email = email,
            phone = phone,
            alamat = alamat
        )
    }
}