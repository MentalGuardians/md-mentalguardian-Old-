package org.guardteam.mentalguardians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !mainViewModel.isLoading.value
        }
        setContent {
            val dynamicColor by mainViewModel.dynamicColor.collectAsStateWithLifecycle(initialValue = true)
            val darkTheme by mainViewModel.darkTheme.collectAsStateWithLifecycle(initialValue = "Automatic")
            MentalGuardiansTheme(
                dynamicColor = dynamicColor,
                darkTheme = when (darkTheme) {
                    "Automatic" -> isSystemInDarkTheme()
                    "Dark" -> true
                    "Light" -> false
                    else -> isSystemInDarkTheme()
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val screen by mainViewModel.startDestination
                    MentalGuardiansApp(
                        startDestination = screen
                    )
                }
            }
        }
    }
}