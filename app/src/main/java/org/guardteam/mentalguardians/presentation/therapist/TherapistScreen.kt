package org.guardteam.mentalguardians.presentation.therapist

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.presentation.component.SearchBar
import org.guardteam.mentalguardians.presentation.component.TherapistItem

@Composable
fun TherapistScreen(
    modifier: Modifier = Modifier,
    active: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    navigateToDetail: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        var query by rememberSaveable {
            mutableStateOf("")
        }
        val searchPadding by animateDpAsState(
            targetValue = if (active) 0.dp else 24.dp,
            animationSpec = tween(300),
            label = "Search Padding"
        )

        SearchBar(query = query, onQueryChange = { newValue ->
            query = newValue
        }, onSearch = {
            onActiveChange(false)
        }, active = active, onActiveChange = { newState ->
            onActiveChange(newState)
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(searchPadding)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(DataDummy.therapistData, key = { it.id }) {
                    TherapistItem(
                        name = it.name,
                        primaryFocus = it.primaryFocus,
                        rating = it.rating,
                        modifier = Modifier
                            .clickable { navigateToDetail(it.id) }
                    )
                }
            }
        }
    }
}