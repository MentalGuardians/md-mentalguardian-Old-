package org.guardteam.mentalguardians.presentation.contentdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ContentDetailScreen(
    contentId: Int,
    modifier: Modifier = Modifier
) {
    val content: Content? = DataDummy.contentData.find { it.id == contentId }
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    content?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Content Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = it.title,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "by ${it.author}",
                        fontFamily = fontFamily,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row {
                        Text(
                            text = it.duration,
                            fontFamily = fontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "Rating Icon",
                            tint = Color.Yellow,
                            modifier = Modifier
                                .height(16.dp)
                                .padding(horizontal = 4.dp)
                        )
                        Text(
                            text = "${it.rating} | ${it.views} View",
                            fontFamily = fontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }


                Icon(
                    imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favorite Button",
                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(40.dp)
                        .aspectRatio(1f)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            isFavorite = !isFavorite
                        }
                )
            }

            Text(
                text = it.desc,
                fontFamily = fontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 24.dp)
            )

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
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "Play Button"
                )

                Text(
                    text = "Play Now",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ContentDetailScreenPreview() {
    MentalGuardiansTheme {
        ContentDetailScreen(contentId = 1)
    }
}