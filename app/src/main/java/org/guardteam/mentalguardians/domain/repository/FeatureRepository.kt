package org.guardteam.mentalguardians.domain.repository

import kotlinx.coroutines.flow.Flow
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.domain.model.Prediction

interface FeatureRepository {

    fun predict(text: String): Flow<Result<Prediction>>
}