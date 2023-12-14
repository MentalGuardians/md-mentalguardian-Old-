package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.ResponseDto
import org.guardteam.mentalguardians.domain.model.Response

fun ResponseDto.toResponse(): Response {
    return Response(
        error = error,
        status = status,
        message = message
    )
}