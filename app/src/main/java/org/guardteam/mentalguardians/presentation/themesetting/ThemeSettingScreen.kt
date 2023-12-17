package org.guardteam.mentalguardians.presentation.themesetting

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ThemeSettingScreen(
    modifier: Modifier = Modifier,
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val dynamicColor by viewModel.dynamicColor.collectAsStateWithLifecycle(initialValue = true)
    val darkTheme by viewModel.darkTheme.collectAsStateWithLifecycle(initialValue = "Automatic")
    val appTheme = listOf("Automatic", "Light", "Dark")
    var dialogState by remember {
        mutableStateOf(false)
    }

    if (dialogState) {
        AlertDialog(
            title = {
                Text(text = "Choose App Theme")
            },
            onDismissRequest = { dialogState = false },
            dismissButton = {
                TextButton(onClick = { dialogState = false }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {},
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(appTheme) {
                        Text(
                            text = it,
                            fontFamily = fontFamily,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.saveDarkTheme(it)
                                    dialogState = false
                                }
                                .padding(12.dp)
                        )
                    }
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Dynamic Color")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = dynamicColor,
                    onCheckedChange = viewModel::saveDynamicColor,
                    colors = SwitchDefaults.colors(
                        uncheckedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        uncheckedTrackColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "App Theme")
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = darkTheme,
                modifier = Modifier.clickable {
                    dialogState = true
                }
            )
        }
    }
}