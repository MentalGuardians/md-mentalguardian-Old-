package org.guardteam.mentalguardians.presentation.history


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.presentation.history.component.BottomSheetContent
import org.guardteam.mentalguardians.presentation.history.component.DaftarHistory
import org.guardteam.mentalguardians.presentation.history.data.DataHistory
import org.guardteam.mentalguardians.presentation.history.data.DummyHistory
import org.guardteam.mentalguardians.presentation.history.data.Mood
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    dummyHistory: List<DummyHistory>,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    var rememberBottomSheet by remember { mutableStateOf(false) }

    if (rememberBottomSheet){
        ModalBottomSheet(onDismissRequest = { rememberBottomSheet = false }) {
            BottomSheetContent(date = "22 November 2023", time = "22: 30 PM", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", mood = Mood.Good)

        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "History",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }
            item {
                Text(
                    text = "Your History",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }
            items(dummyHistory) { data ->
                DaftarHistory(
                    date = data.history.date,
                    time = data.history.time,
                    modifier = modifier.clickable {
                        rememberBottomSheet = true
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun HistoryPreview(){
    val dummyHistory = listOf(
        DummyHistory(DataHistory(1,"22 November 2023", "20:30 PM","nani")),
        DummyHistory(DataHistory(2,"30 November 2023", "19:30 PM","haa")),
        DummyHistory(DataHistory(3,"30 November 2023", "19:30 PM","haa")),
        DummyHistory(DataHistory(4,"30 November 2023", "19:30 PM","haa"))
    )
    HistoryScreen(dummyHistory = dummyHistory)
}