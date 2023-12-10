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
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.presentation.history.component.BottomSheetContent
import org.guardteam.mentalguardians.presentation.history.component.HistoryItem
import org.guardteam.mentalguardians.presentation.history.data.Mood
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
) {
    var bottomSheetState by remember { mutableStateOf(false) }

    if (bottomSheetState) {
        ModalBottomSheet(onDismissRequest = { bottomSheetState = false }) {
            BottomSheetContent(
                date = "22 November 2023",
                time = "22: 30 PM",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                mood = Mood.Good
            )
        }
    }

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

        LazyColumn {
            items(DataDummy.historyData, key = { it.id }) {
                HistoryItem(
                    date = it.date,
                    time = it.time,
                    modifier = Modifier.clickable {
                        bottomSheetState = true
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun HistoryPreview() {
    HistoryScreen()
}