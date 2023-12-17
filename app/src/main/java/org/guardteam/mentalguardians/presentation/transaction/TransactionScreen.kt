package org.guardteam.mentalguardians.presentation.transaction

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.domain.model.TransactionData
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.presentation.transaction.component.TransactionBottomSheet
import org.guardteam.mentalguardians.presentation.transaction.component.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    var bottomSheetState by remember { mutableStateOf(false) }
    val bottomSheetData by viewModel.transactionData.collectAsStateWithLifecycle()
    val result by viewModel.result.collectAsStateWithLifecycle()
    val cancel by viewModel.cancel.collectAsStateWithLifecycle()

    if (!cancel.hasBeenHandled) {
        when (cancel.getContentIfNotHandled()) {
            is Result.Success -> {
                bottomSheetState = false
                viewModel.getTransaction()
            }

            is Result.Error -> {
                Toast.makeText(LocalContext.current, "Cancel Booking Failed", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
        }
    }

    if (bottomSheetState) {
        ModalBottomSheet(onDismissRequest = { bottomSheetState = false }) {
            TransactionBottomSheet(
                psychologistName = bottomSheetData.therapistName,
                date = bottomSheetData.sessionDate,
                time = bottomSheetData.sessionTime,
                method = bottomSheetData.sessionMethod,
                status = bottomSheetData.status,
                link = bottomSheetData.link,
                bookDate = bottomSheetData.bookingDate,
                onClick = {
                    viewModel.cancelBooking(bottomSheetData.bookingId)
                },
                buttonActive = cancel.peekContent() !is Result.Loading
            )
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

        when (val resultData = result) {
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
                        onClick = { viewModel.getTransaction() },
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
                TransactionContent(
                    transaction = resultData.data.historyBooking,
                    onBottomSheetStateChange = {
                        bottomSheetState = it
                    },
                    onBottomSheetDataChange = {
                        viewModel.setTransactionData(it)
                    }
                )
            }

            else -> {}
        }
    }
}

@Composable
fun TransactionContent(
    transaction: List<TransactionData>,
    onBottomSheetStateChange: (Boolean) -> Unit,
    onBottomSheetDataChange: (TransactionData) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 24.dp),
        modifier = modifier
    ) {
        items(transaction, key = { it.bookingId }) {
            TransactionItem(
                name = it.therapistName,
                date = it.sessionDate,
                time = it.sessionTime,
                status = it.status,
                modifier = Modifier.clickable {
                    onBottomSheetDataChange(it)
                    onBottomSheetStateChange(true)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    TransactionScreen()
}