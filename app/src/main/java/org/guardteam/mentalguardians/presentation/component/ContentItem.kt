package org.guardteam.mentalguardians.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ContentItem(
    author: String,
    title: String,
    duration: String,
    rating: Double,
    views: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.content_cover),
                contentDescription = "Content Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(100.dp)
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = author,
                    fontFamily = fontFamily,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = title,
                    fontFamily = fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = duration,
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
                        text = "$rating | $views View",
                        fontFamily = fontFamily,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContentItemPreview() {
    MentalGuardiansTheme {
        ContentItem(
            author = "Anonymous",
            title = "Sleeping with Beautiful",
            duration = "2-3 min",
            rating = 4.8,
            views = "2.4k"
        )
    }
}
