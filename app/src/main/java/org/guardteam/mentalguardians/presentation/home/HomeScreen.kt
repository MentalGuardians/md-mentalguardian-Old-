package org.guardteam.mentalguardians.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //AsyncImage(model = R.drawable.profile_sample, contentDescription = "Profile photo")
            Image(
                painter = painterResource(id = R.drawable.profile_sample),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
            )
            Text(
                text = "Users",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 12.dp)
            )
        }

        Text(
            text = "Hi There\nGood to see you again  \uD83D\uDE4C",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable { }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(
                    text = "How's your feeling today?",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                Text(
                    text = "Describe your current  condition to get the result",
                    fontFamily = fontFamily,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )

                Text(
                    text = "Try it Now !!",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }

        Text(
            text = "Features",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        FeaturesItem(
            label = "Self Help",
            content = "Need Helpful Content?",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        FeaturesItem(
            label = "Counseling",
            content = "Need a Therapist?",
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun FeaturesItem(
    label: String,
    content: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = fontFamily,
                fontSize = 14.sp
            )
            Text(
                text = content,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily,
                fontSize = 16.sp
            )
            Text(
                text = "Click Here",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = fontFamily,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun FeaturesItemPreview() {
    MentalGuardiansTheme {
        FeaturesItem(
            label = "Self Help",
            content = "Need Helpful Content?"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MentalGuardiansTheme {
        HomeScreen()
    }
}