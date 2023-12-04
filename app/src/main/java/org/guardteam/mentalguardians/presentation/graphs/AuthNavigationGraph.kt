package org.guardteam.mentalguardians.presentation.graphs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.guardteam.mentalguardians.common.Graph
import org.guardteam.mentalguardians.presentation.signin.SignInScreen
import org.guardteam.mentalguardians.presentation.signup.SignUpScreen
import org.guardteam.mentalguardians.presentation.welcome.WelcomeScreen

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Welcome.route
    ) {
        composable(route = AuthScreen.Welcome.route) {
            WelcomeScreen(
                navigateToSignIn = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.SignIn.route)
                }
            )
        }
        composable(route = AuthScreen.SignIn.route) {
            SignInScreen(
                navigateSignUp = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                backToSignIn = {
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen(route = "welcome")
    data object SignIn : AuthScreen(route = "signin")

    data object SignUp : AuthScreen(route = "signup")
}