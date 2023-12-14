package org.guardteam.mentalguardians.domain.use_case

data class UserDataUseCase(
    val getUserData: GetUserData,
    val saveUserData: SaveUserData,
    val getLoginState: GetLoginState,
    val clearUserData: ClearUserData
)
