package org.guardteam.mentalguardians.domain.use_case

data class ThemeUseCase(
    val getDynamicColor: GetDynamicColor,
    val getDarkTheme: GetDarkTheme,
    val saveDynamicColor: SaveDynamicColor,
    val saveDarkTheme: SaveDarkTheme
)
