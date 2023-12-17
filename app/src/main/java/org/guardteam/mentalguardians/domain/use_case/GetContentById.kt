package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetContentById(
    private val repository: FeatureRepository
) {
    operator fun invoke(contentId: String): Flow<Result<ContentById>> {
        return repository.contentById(contentId)
    }
}