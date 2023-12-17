package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.ProfileDto
import org.guardteam.mentalguardians.domain.model.Profile

fun ProfileDto.toProfileData() : Profile {
    return Profile(
        id = userId,
        account = email,
        username = username,
        address = alamat,
        telephone = phone,
    )
}