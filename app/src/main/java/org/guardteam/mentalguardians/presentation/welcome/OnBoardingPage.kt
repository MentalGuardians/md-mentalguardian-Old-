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
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.blaming,
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur"
    )

    data object Third : OnBoardingPage(
        image = R.drawable.schizophrenia,
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur"
    )
}