package org.guardteam.mentalguardians.domain.use_case

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.repository.FeatureRepository

class GetContent(
    private val featureRepository: FeatureRepository
) {
    operator fun invoke(content: String): Flow<Result<Content>> {
        return featureRepository.content(content)
    }
}