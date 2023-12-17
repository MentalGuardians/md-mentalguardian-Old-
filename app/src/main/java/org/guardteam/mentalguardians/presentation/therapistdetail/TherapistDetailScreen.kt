package org.guardteam.mentalguardians.presentation.therapistdetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BusinessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.utils.listDate
import org.guardteam.mentalguardians.utils.listTime
import org.guardteam.mentalguardians.domain.model.TherapistData
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.presentation.therapistdetail.component.DateItem
import org.guardteam.mentalguardians.presentation.therapistdetail.component.MethodItem
import org.guardteam.mentalguardians.presentation.therapistdetail.component.TimeItem

@Composable
fun TherapistDetailScreen(
    therapistId: String,
    modifier: Modifier = Modifier,
    navigateToTransaction: () -> Unit = {},
    viewModel: TherapistDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(therapistId) {
        viewModel.getTherapistById(therapistId)
    }

    var selectedDate by rememberSaveable {
        mutableStateOf("")
    }

    var selectedTime by rememberSaveable {
        mutableStateOf("")
    }

    var selectedMethod by rememberSaveable {
        mutableStateOf("")
    }

    val result by viewModel.result.collectAsStateWithLifecycle()
    val booking by viewModel.booking.collectAsStateWithLifecycle()

    if (!booking.hasBeenHandled) {
        when (val unhandled = booking.getContentIfNotHandled()) {
            is Result.Error -> {
                Toast.makeText(context, unhandled.error, Toast.LENGTH_SHORT).show()
            }

            is Result.Success -> {
                Toast.makeText(context, unhandled.data.message, Toast.LENGTH_SHORT).show()
                navigateToTransaction()
            }

            else -> {}
        }
    }

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
                    onClick = { viewModel.getTherapistById(therapistId) },
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
            TherapistDetailContent(
                therapistData = resultData.data.data,
                selectedDate = selectedDate,
                selectedTime = selectedTime,
                isButtonEnabled = booking.peekContent() !is Result.Loading,
                selectedMethod = selectedMethod,
                onSelectedDateChange = {
                    selectedDate = it
                },
                onSelectedTimeChange = {
                    selectedTime = it
                },
                onSelectedMethodChange = {
                    selectedMethod = it
                },
                onButtonClicked = {
                    when {
                        selectedDate.isEmpty() || selectedTime.isEmpty() || selectedMethod.isEmpty() -> {}
                        else -> {
                            viewModel.postBooking(
                                therapistId = resultData.data.data.therapistId,
                                date = selectedDate,
                                time = selectedTime,
                                method = selectedMethod
                            )
                        }
                    }
                }
            )
        }

        else -> {}
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TherapistDetailContent(
    therapistData: TherapistData,
    selectedDate: String,
    selectedTime: String,
    selectedMethod: String,
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
    onSelectedDateChange: (String) -> Unit = {},
    onSelectedTimeChange: (String) -> Unit = {},
    onSelectedMethodChange: (String) -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.therapist_profile),
                contentDescription = "Therapist Profile",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(95.dp)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = therapistData.name,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = therapistData.status,
                    fontFamily = fontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = therapistData.category,
                    fontFamily = fontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "${therapistData.age} Years old ${therapistData.gender}",
                    fontFamily = fontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = therapistData.price,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Per Hour", fontFamily = fontFamily)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = therapistData.method,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Method", fontFamily = fontFamily)
            }

            if (therapistData.domicile != "Online") {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = therapistData.domicile,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(text = "Domicile", fontFamily = fontFamily)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Select a day",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            listDate.forEach { day ->
                DateItem(
                    date = day.day,
                    day = day.dayName.lowercase().replaceFirstChar(Char::uppercase),
                    selected = day.date == selectedDate,
                    modifier = Modifier
                        .clickable {
                            onSelectedDateChange(day.date)
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Select an hour",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = 12.dp
            )
        ) {
            listTime.forEach { data ->
                TimeItem(
                    time = data.first,
                    period = data.second,
                    selected = data.first == selectedTime,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelectedTimeChange(data.first) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Select Method",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row {
            if (therapistData.method == "Hybrid") {
                MethodItem(
                    name = "Online",
                    selected = selectedMethod == "online",
                    modifier = Modifier.clickable { onSelectedMethodChange("online") })
                Spacer(modifier = Modifier.width(12.dp))
                MethodItem(
                    name = "Offline",
                    selected = selectedMethod == "offline",
                    modifier = Modifier.clickable { onSelectedMethodChange("offline") }
                )
            } else {
                MethodItem(
                    name = therapistData.method,
                    selected = selectedMethod == therapistData.method.toLowerCase(Locale.current),
                    modifier = Modifier.clickable {
                        onSelectedMethodChange(
                            therapistData.method.toLowerCase(
                                Locale.current
                            )
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            enabled = isButtonEnabled,
            onClick = onButtonClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.BusinessCenter,
                contentDescription = "Book Button"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Make an appointment",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TherapistDetailScreenPreview() {
    MentalGuardiansTheme {
        TherapistDetailContent(
            selectedDate = "2023-12-16",
            selectedTime = "09:00",
            selectedMethod = "online",
            isButtonEnabled = true,
            therapistData =
            TherapistData(
                therapistId = "cjqbvoj7m1X9fB4i",
                age = 21,
                category = "Family",
                domicile = "Bandung",
                gender = "Female",
                method = "Hybrid",
                name = "Monica Putri Yani",
                price = "Rp85.000,00",
                rating = "3,5",
                status = "Psychologist",
                viewed = 200,
            )
        )
    }
}