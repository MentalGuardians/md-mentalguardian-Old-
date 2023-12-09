package org.guardteam.mentalguardians.presentation.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mood
import androidx.compose.material.icons.rounded.MoodBad
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.presentation.history.data.Mood
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    description: String,
    mood: Mood
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column {
                Text(text = date, style = MaterialTheme.typography.bodyMedium)
                Text(text = time, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = modifier.weight(1F))
            MoodIcon(mood = mood)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, style = MaterialTheme.typography.bodyMedium)
    }

}

@Composable
fun MoodIcon(
    modifier: Modifier = Modifier,
    mood: Mood
) {
    val icon = when (mood) {
       Mood.Bad -> Icons.Rounded.MoodBad
        Mood.Good -> Icons.Rounded.Mood
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier
            .size(36.dp)
            .background(
                color = if (mood == Mood.Good) MaterialTheme.colorScheme.primary else Color.Red,
                shape = CircleShape
            )
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun BSPreview(){
    MentalGuardiansTheme {
        BottomSheetContent(date = "22 november 20023", time = "22.30 PM", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", mood = Mood.Bad)
    }
}