package org.guardteam.mentalguardians.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit = {}
) {
    androidx.compose.material3.SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        shape = RoundedCornerShape(16.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
        },
        placeholder = {
            Text(
                text = "Search",
                fontFamily = fontFamily
            )
        },
        trailingIcon = trailingIcon,
        modifier = modifier
    ) {

    }
}

@Preview
@Composable
fun SearchBarActivePreview() {
    MentalGuardiansTheme {
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {}
        )
    }
}

@Preview
@Composable
fun SearchBarInactivePreview() {
    MentalGuardiansTheme {
        SearchBar(query = "", onQueryChange = {}, onSearch = {}, active = true, onActiveChange = {})
    }
}