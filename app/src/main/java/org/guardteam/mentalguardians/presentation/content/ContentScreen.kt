package org.guardteam.mentalguardians.presentation.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.presentation.content.component.ContentItem
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit = {},
    content: String = "",
    viewModel: ContentViewModel = hiltViewModel()
) {

    val result by viewModel.result.collectAsStateWithLifecycle()

    LaunchedEffect(content) {
        viewModel.getContent(content)
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
                    onClick = { viewModel.getContent(content) },
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
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    items(resultData.data.result, key = { it.contentId }) {
                        ContentItem(
                            author = it.author,
                            title = it.title,
                            label = it.labels,
                            likes = it.likes,
                            views = it.views,
                            image = it.thumbnail,
                            modifier = Modifier
                                .clickable { navigateToDetail(it.contentId) }
                        )
                    }
                }
            }
        }

        else -> {}
    }

}

@Preview
@Composable
fun ContentScreenPreview() {
    MentalGuardiansTheme {
        ContentScreen()
    }
}