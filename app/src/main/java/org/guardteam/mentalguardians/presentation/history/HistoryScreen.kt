package org.guardteam.mentalguardians.presentation.history


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.history.component.BottomSheetContent
import org.guardteam.mentalguardians.presentation.history.component.HistoryItem
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val history by viewModel.history.collectAsState()
    val bottomSheetState by viewModel.bottomSheetState.collectAsState()
    val bottomSheetData by viewModel.bottomSheetData.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = "History",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "Your History",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        when (val historyData = history) {
            is Result.Loading -> {
                StatusItem(
                    modifier = modifier,
                    status = "Loading"
                )
            }

            is Result.Error -> {
                StatusItem(
                    modifier = modifier,
                    status = "an error has occurred"
                ) {
                    Button(
                        onClick = { viewModel.getHistory() },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Reload",
                            fontFamily = fontFamily
                        )
                    }
                }
            }

            is Result.Success -> {
                LazyColumn {
                    items(historyData.data.historyData, key = { it.id }) {
                        HistoryItem(
                            date = it.date,
                            modifier = modifier.clickable {
                                viewModel.onHistoryItemClicked()
                                viewModel.setBottomSheetData(it)
                            }
                        )
                    }
                }
            }

            else -> {}
        }
    }
    if (bottomSheetState) {
        ModalBottomSheet(onDismissRequest = { viewModel.onDismissBottomSheet() }) {
            BottomSheetContent(
                date = bottomSheetData.date,
                description = bottomSheetData.diagnose,
                mood = bottomSheetData.mood
            )
        }
    }


}

@Preview(showBackground = true, device = Devices.DEFAULT, showSystemUi = true)
@Composable
fun HistoryPreview() {
}