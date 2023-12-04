package org.guardteam.mentalguardians

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.guardteam.mentalguardians.presentation.graphs.MainNavGraph
import org.guardteam.mentalguardians.presentation.graphs.RootNavGraph
import org.guardteam.mentalguardians.presentation.main.MainScreen
import org.guardteam.mentalguardians.presentation.signin.SignInScreen
import org.guardteam.mentalguardians.presentation.signup.SignUpScreen
import org.guardteam.mentalguardians.presentation.welcome.WelcomeScreen

@ExperimentalFoundationApi
@Composable
fun MentalGuardiansApp(
) {
    RootNavGraph()
}