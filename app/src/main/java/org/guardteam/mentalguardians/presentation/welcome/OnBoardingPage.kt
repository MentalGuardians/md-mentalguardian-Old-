package org.guardteam.mentalguardians.presentation.welcome

import androidx.annotation.DrawableRes
import org.guardteam.mentalguardians.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnBoardingPage(
        image = R.drawable.meditation,
        title = "For You",
        description = "Calm your day\nwith Mental \nGuardian"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.counseling,
        title = "Counseling",
        description = "Get special\nessions with \nExperts"
    )

    data object Third : OnBoardingPage(
        image = R.drawable.content,
        title = "Self Meditation",
        description = "Enjoy calming\nand educational \ncontent"
    )
}