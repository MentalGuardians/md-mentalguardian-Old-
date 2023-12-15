package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.ContentByIdDto
import org.guardteam.mentalguardians.data.remote.dto.ContentDataDto
import org.guardteam.mentalguardians.data.remote.dto.ContentDto
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.model.ContentData

fun ContentDto.toContent(): Content {
    return Content(
        statusCode = statusCode,
        message = message,
        result = result.map { it.toContentData() }
    )
}

fun ContentDataDto.toContentData(): ContentData {
    return ContentData(
        contentId = contentId,
        title = title,
        author = author,
        labels = labels,
        comments = comments,
        likes = likes,
        videoId = videoId,
        views = views,
        thumbnail = thumbnail
    )
}

fun ContentByIdDto.toContentById(): ContentById {
    return ContentById(
        error = error,
        status = status,
        message = message,
        data = data.toContentData()
    )
}