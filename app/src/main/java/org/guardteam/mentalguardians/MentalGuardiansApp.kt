package org.guardteam.mentalguardians

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import org.guardteam.mentalguardians.presentation.graphs.RootNavGraph

@ExperimentalFoundationApi
@Composable
fun MentalGuardiansApp(
    startDestination: String
) {
    RootNavGraph(
        startDestination = startDestination
    )
}