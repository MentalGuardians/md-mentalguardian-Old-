package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.PredictionDto
import org.guardteam.mentalguardians.domain.model.Prediction

fun PredictionDto.toPrediction(): Prediction {
    return Prediction(
        error = error,
        status = status,
        message = message,
        prediction = prediction
    )
}