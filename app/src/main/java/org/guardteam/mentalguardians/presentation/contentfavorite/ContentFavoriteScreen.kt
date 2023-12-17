package org.guardteam.mentalguardians.presentation.contentfavorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme

@Composable
fun ContentFavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            contentPadding = PaddingValues(vertical = 24.dp)
//        ) {
//            items(DataDummy.contentData, key = { it.id }) {
//                ContentItem(
//                    author = it.author,
//                    title = it.title,
//                    duration = it.duration,
//                    rating = it.rating,
//                    views = it.views,
//                    modifier = Modifier.clickable { navigateToDetail(it.id) }
//                )
//            }
//        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContentFavoriteScreenPreview() {
    MentalGuardiansTheme {
        ContentFavoriteScreen()
    }
}