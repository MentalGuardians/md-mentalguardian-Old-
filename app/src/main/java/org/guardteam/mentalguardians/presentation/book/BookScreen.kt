package org.guardteam.mentalguardians.presentation.book

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookScreen(
    therapistId: Int,
    modifier: Modifier = Modifier
) {

    val therapist: Therapist? = DataDummy.therapistData.find { it.id == therapistId }
    var selectedDate by rememberSaveable {
        mutableStateOf("")
    }

    var selectedTime by rememberSaveable {
        mutableStateOf("")
    }

    therapist?.let {
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
                        .height(75.dp)
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = it.name,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = it.primaryFocus,
                        fontFamily = fontFamily,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow
                        )

                        Text(
                            text = it.rating.toString(),
                            fontFamily = fontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
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
                DataDummy.listDate.forEach { day ->
                    DateItem(
                        date = day.day,
                        day = day.dayName.lowercase().replaceFirstChar(Char::uppercase),
                        selected = day.date == selectedDate,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                selectedDate = day.date
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
                DataDummy.listTime.forEach { data ->
                    TimeItem(
                        time = data.first,
                        period = data.second,
                        selected = data.first == selectedTime,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTime = data.first }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                },
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
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = "Book Button"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Book",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun DateItem(
    date: Int,
    day: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(
            width = 2.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(62.dp)
            .height(85.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = day,
                fontFamily = fontFamily,
                fontSize = 16.sp
            )

            Text(
                text = date.toString(),
                fontFamily = fontFamily,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun TimeItem(
    time: String,
    period: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(
            width = 2.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Rounded.AccessTime, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "$time $period")
        }
    }
}

@Preview
@Composable
fun TimeItemPreview() {
    MentalGuardiansTheme {
        TimeItem(
            time = "09:00",
            period = "am",
            selected = false
        )
    }
}

@Preview
@Composable
fun TimeItemPreviewSelected() {
    MentalGuardiansTheme {
        TimeItem(
            time = "09:00",
            period = "am",
            selected = true
        )
    }
}

@Preview
@Composable
fun BookScreenPreview() {
    MentalGuardiansTheme {
        BookScreen(therapistId = 1)
    }
}