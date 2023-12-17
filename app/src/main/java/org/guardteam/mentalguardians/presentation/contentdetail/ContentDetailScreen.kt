package org.guardteam.mentalguardians.presentation.contentdetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Comment
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.domain.model.ContentData
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.utils.Result

@Composable
fun ContentDetailScreen(
    contentId: String,
    modifier: Modifier = Modifier,
    viewModel: ContentDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(contentId) {
        viewModel.getContentById(contentId)
    }

    val result by viewModel.result.collectAsStateWithLifecycle()

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
                    onClick = { viewModel.getContentById(contentId) },
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
            ContentDetailContent(contentData = resultData.data.data)
        }

        else -> {}
    }

}

@Composable
fun ContentDetailContent(
    contentData: ContentData,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = contentData.thumbnail,
            error = painterResource(id = R.drawable.img),
            placeholder = painterResource(id = R.drawable.img),
            contentDescription = "Content Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = contentData.title,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "by ${contentData.author}",
            fontFamily = fontFamily,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = contentData.labels,
            fontFamily = fontFamily,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.ThumbUp,
                contentDescription = "Rating Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${contentData.likes}",
                fontFamily = fontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Rounded.Comment,
                contentDescription = "Comment",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${contentData.comments}",
                fontFamily = fontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Rounded.Visibility,
                contentDescription = "Comment",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${contentData.views}",
                fontFamily = fontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "this button with navigate you to youtube app",
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()

                .padding(top = 24.dp)
        )
        Button(
            onClick = {
                val uri = Uri.parse(contentData.videoId)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "Play Button"
            )

            Text(
                text = "Play Now",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

    }
}

@Preview
@Composable
fun ContentDetailContentPreview() {
    MentalGuardiansTheme {
        ContentDetailContent(
            ContentData(
                contentId = "VD4foZh5QTtrbD6-",
                title = "Effects of Bullying",
                author = "Everyday Health",
                labels = "Bullying",
                comments = 3,
                likes = 33,
                videoId = "https://www.youtube.com/watch?v=KxuTU5q9epc",
                views = 6848,
                thumbnail = "https://i.ytimg.com/vi/KxuTU5q9epc/hqdefault.jpg"
            )
        )
    }
}