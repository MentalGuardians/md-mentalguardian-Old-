package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.TherapistByIdDto
import org.guardteam.mentalguardians.data.remote.dto.TherapistDto
import org.guardteam.mentalguardians.data.remote.dto.TherapitDataDto
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.domain.model.TherapistById
import org.guardteam.mentalguardians.domain.model.TherapistData

fun TherapistDto.toTherapist(): Therapist {
    return Therapist(
        statusCode = statusCode,
        message = message,
        result = result.map { it.toTherapistData() })
}

fun TherapistByIdDto.toTherapistById(): TherapistById {
    return TherapistById(
        error = error,
        status = status,
        message = message,
        data = data.toTherapistData()
    )
}

fun TherapitDataDto.toTherapistData(): TherapistData {
    return TherapistData(
        therapistId = therapistId,
        age = age,
        category = category,
        domicile = domicile,
        gender = gender,
        method = method,
        name = name,
        price = price,
        rating = rating,
        status = status,
        viewed = viewed
    )
}