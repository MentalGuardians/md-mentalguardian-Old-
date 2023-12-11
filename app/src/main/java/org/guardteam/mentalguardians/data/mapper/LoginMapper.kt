package org.guardteam.mentalguardians.data.mapper

import org.guardteam.mentalguardians.data.remote.dto.LoginDataDto
import org.guardteam.mentalguardians.data.remote.dto.LoginDto
import org.guardteam.mentalguardians.domain.model.Login
import org.guardteam.mentalguardians.domain.model.LoginData

fun LoginDataDto.toLoginData(): LoginData {
    return LoginData(
        userId = userId,
        username = username,
        email = email,
        picture = picture
    )
}

fun LoginDto.toLogin(): Login {
    return Login(
        error = error,
        status = status,
        message = message,
        loginResult = loginResult.toLoginData()
    )
}