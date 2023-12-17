package org.guardteam.mentalguardians.presentation.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.guardteam.mentalguardians.presentation.common.InputTextState
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.presentation.component.InputText
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToContent: () -> Unit = {},
    navigateToTherapist: () -> Unit = {},
    navigateToPredict: (String) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    var openAlertDialog by rememberSaveable { mutableStateOf(false) }
    val describeState by viewModel.describeState
    val result by viewModel.result.collectAsStateWithLifecycle()
    val profile by viewModel.profile

    result.let {
        val context = LocalContext.current
        if (!result.hasBeenHandled) {
            when (val unhandled = result.getContentIfNotHandled()) {
                is Result.Error -> {
                    Toast.makeText(context, unhandled.error, Toast.LENGTH_SHORT).show()
                }

                is Result.Success -> {
                    openAlertDialog = false
                    viewModel.setDescribeState(InputTextState())
                    navigateToPredict(unhandled.data.prediction)
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = profile.picture,
                error = painterResource(id = R.drawable.profile_sample),
                placeholder = painterResource(id = R.drawable.profile_sample),
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
            )
            Text(
                text = profile.username,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 12.dp)
            )
        }

        Text(
            text = "Hi There\nGood to see you again  \uD83D\uDE4C",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        Card(shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable { openAlertDialog = true }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "How's your feeling today?",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )

                Text(
                    text = "Describe your current  condition to get the result",
                    fontFamily = fontFamily,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )

                Text(
                    text = "Try it Now !!",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }

        Text(
            text = "Features",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        FeaturesItem(
            label = "Self Help",
            content = "Need Helpful Content?",
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = navigateToContent
        )

        FeaturesItem(
            label = "Counseling",
            content = "Need a Therapist?",
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = navigateToTherapist
        )
    }
    when {
        openAlertDialog -> {
            AlertDialog(title = {
                Text(text = "Describe your current condition")
            }, text = {
                Column {
                    InputText(value = describeState.value,
                        onChange = { newValue ->
                            viewModel.setDescribeState(
                                describeState.copy(
                                    value = newValue, isError = newValue.isEmpty()
                                )
                            )
                        },
                        isError = describeState.isError,
                        label = "Describe",
                        singleLine = false,
                        minLine = 3,
                        colors = TextFieldDefaults.colors(
                            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                4.dp
                            ),
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                4.dp
                            ),
                            errorContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                4.dp
                            )
                        ),
                        supportingText = {
                            if (describeState.isError) {
                                Text(text = "Cannot be empty", fontFamily = fontFamily)
                            }
                        })

                    if (result.peekContent() is Result.Loading) {
                        Text(
                            text = "Please wait",
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

            }, onDismissRequest = {
                if (result.peekContent() !is Result.Loading) {
                    openAlertDialog = false
                    viewModel.setDescribeState(InputTextState())
                }
            }, dismissButton = {
                TextButton(
                    enabled = result.peekContent() !is Result.Loading,
                    onClick = {
                        openAlertDialog = false
                        viewModel.setDescribeState(InputTextState())

                    }) {
                    Text(text = "Cancel")
                }
            }, confirmButton = {
                TextButton(
                    enabled = result.peekContent() !is Result.Loading,
                    onClick = {
                        viewModel.getPredict()
                    }) {
                    Text(text = "Submit")
                }
            })
        }
    }
}

@Composable
fun FeaturesItem(
    label: String, content: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Card(shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = fontFamily,
                fontSize = 14.sp
            )
            Text(
                text = content,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                fontFamily = fontFamily,
                fontSize = 16.sp
            )
            Text(
                text = "Click Here",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = fontFamily,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun FeaturesItemPreview() {
    MentalGuardiansTheme {
        FeaturesItem(
            label = "Self Help", content = "Need Helpful Content?"
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MentalGuardiansTheme {
        HomeScreen()
    }
}