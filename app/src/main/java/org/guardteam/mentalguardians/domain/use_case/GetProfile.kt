package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.domain.repository.FeatureRepository
import org.guardteam.mentalguardians.presentation.profile.data.Profile

class GetProfile(
    private val repository: FeatureRepository
) {
    operator fun invoke():Flow<Result<Profile>> {
        return repository.profile()
    }
}