package org.guardteam.mentalguardians.domain.repository

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.model.Prediction

interface FeatureRepository {

    fun predict(text: String): Flow<Result<Prediction>>

    fun content(content: String): Flow<Result<Content>>

    fun contentById(contentId: String): Flow<Result<ContentById>>
}