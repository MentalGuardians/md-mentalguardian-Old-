package org.guardteam.mentalguardians.presentation.therapist

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.presentation.component.StatusItem
import org.guardteam.mentalguardians.presentation.therapist.component.TherapistItem
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun TherapistScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit = {},
    expert: String = "",
    viewModel: TherapistViewModel = hiltViewModel()
) {

    val result by viewModel.result.collectAsStateWithLifecycle()

    LaunchedEffect(expert) {
        viewModel.getTherapist(expert)
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
                    onClick = { viewModel.getTherapist(expert) },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    items(resultData.data.result, key = { it.therapistId }) {
                        TherapistItem(
                            name = it.name,
                            primaryFocus = it.category,
                            rating = it.rating,
                            status = it.status,
                            method = it.method,
                            modifier = Modifier
                                .clickable {
                                    navigateToDetail(it.therapistId)
                                }
                        )
                    }
                }
            }
        }

        else -> {}
    }
}