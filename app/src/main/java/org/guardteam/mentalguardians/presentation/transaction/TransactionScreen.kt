package org.guardteam.mentalguardians.presentation.transaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.presentation.transaction.component.TransactionItem
import org.guardteam.mentalguardians.presentation.transaction.component.TransactionBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
) {
    var bottomSheetState by remember { mutableStateOf(false) }

    if (bottomSheetState) {
        ModalBottomSheet(onDismissRequest = { bottomSheetState = false }) {
            TransactionBottomSheet(
                psychologistName = "Ihfansyah Pedo",
                date = "23 November 2023",
                time = "23.30",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                link = "https://meet.google.com/tsi/frfb-nzt",
                status = "Scheduled",
                whatsapp = "+6282146804920"
            ) {

            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = "Transaction",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Your Transaction",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(DataDummy.dataTransaction, key = { it.id }) {
                TransactionItem(
                    name = it.name,
                    date = it.date,
                    time = it.time,
                    status = it.status,
                    modifier = Modifier.clickable {
                        bottomSheetState = true
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    TransactionScreen()
}