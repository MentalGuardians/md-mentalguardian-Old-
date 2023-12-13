package org.guardteam.mentalguardians.presentation.history.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.presentation.history.data.Mood
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun BottomSheetContent(
    date: String,
    time: String,
    description: String,
    mood: Mood,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column {
                Text(
                    text = date,
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = time,
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = modifier.weight(1F))
            MoodIcon(mood = mood)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontFamily = fontFamily,
            fontSize = 16.sp
        )
    }

}

@Composable
fun MoodIcon(
    modifier: Modifier = Modifier,
    mood: Mood
) {
    val icon = when (mood) {
        Mood.Bad -> R.drawable.ic_negative
        Mood.Good -> R.drawable.ic_positive
    }

    val color = when (mood) {
        Mood.Bad -> Color.Red
        Mood.Good -> Color.Green
    }

    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = color,
        modifier = modifier
            .size(36.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BSPreview() {
    MentalGuardiansTheme {
        BottomSheetContent(
            date = "22 november 20023",
            time = "22.30 PM",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            mood = Mood.Bad
        )
    }
}