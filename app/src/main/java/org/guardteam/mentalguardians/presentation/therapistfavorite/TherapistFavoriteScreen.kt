package org.guardteam.mentalguardians.presentation.therapistfavorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TherapistFavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit = {}
) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(24.dp)
//    ) {
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            contentPadding = PaddingValues(vertical = 24.dp)
//        ) {
//            items(DataDummy.therapistData, key = { it.id }) {
//                TherapistItem(
//                    name = it.name,
//                    primaryFocus = it.primaryFocus,
//                    rating = it.rating,
//                    modifier = Modifier.clickable { navigateToDetail(it.id) }
//                )
//            }
//        }
//    }
}