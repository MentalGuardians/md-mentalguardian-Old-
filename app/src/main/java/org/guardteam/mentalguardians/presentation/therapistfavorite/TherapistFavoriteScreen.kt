package org.guardteam.mentalguardians.presentation.therapistfavorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.presentation.component.TherapistItem

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