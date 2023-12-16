package org.guardteam.mentalguardians.presentation.history


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.common.utils.Result
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
        when(val historyData = history){
            is Result.Loading -> {

            }

            is Result.Error -> {

            }

            is  Result.Success -> {
                Column(modifier = modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()) {
                    LazyColumn(){
                        items(historyData.data.historyData, key = {it.id}){
                            HistoryItem(
                                date = it.date,
                                modifier = modifier.clickable { viewModel.onHistoryItemClicked() }
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
    when(val bottomSheet = history){
        is Result.Loading -> {

        }

        is Result.Error -> {

        }

        is Result.Success -> {
            if (bottomSheetState) {
                ModalBottomSheet(onDismissRequest = { viewModel.onDismissBottomSheet() }) {
                    LazyColumn(){
                        items(bottomSheet.data.historyData, key = {it.id}){
                            BottomSheetContent(
                                date = it.date,
                                description = it.diagnose,
                                mood = it.mood)
                        }
                    }
                }
            }
        }

        else -> {}
    }


}

@Preview(showBackground = true, device = Devices.DEFAULT, showSystemUi = true)
@Composable
fun HistoryPreview() {
    HistoryScreen()
}