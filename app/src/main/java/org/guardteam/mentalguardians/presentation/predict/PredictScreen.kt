package org.guardteam.mentalguardians.presentation.predict

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.presentation.component.IssueChip
import org.guardteam.mentalguardians.presentation.history.data.Mood
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PredictScreen(
    moodResult: String,
    modifier: Modifier = Modifier,
    viewModel: PredictViewModel = hiltViewModel(),
    backToHome: () -> Unit = {},
    navigateToContent: (String) -> Unit = {},
    navigateToTherapist: (String) -> Unit = {}
) {
    val mood = Mood.byNameIgnoreCaseOrNull(moodResult)
    val icon = when (mood) {
        Mood.Bad -> R.drawable.ic_negative
        Mood.Good -> R.drawable.ic_positive
    }

    val textResult = when (mood) {
        Mood.Bad -> "You don't seem to be okay\n\nDo you mind if we know what your problems are?"
        Mood.Good -> "You seem to be fine, great..."
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Result",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = textResult,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (mood == Mood.Bad) {
            val chipList = viewModel.chipState
            FlowRow(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(
                    12.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                chipList.forEachIndexed { index, chip ->
                    IssueChip(
                        value = chip.value,
                        selected = chip.selected,
                        onClick = {
                            viewModel.setChipSelectedAtIndex(index, !chip.selected)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Need therapist or helpful Content ?",
                fontFamily = fontFamily,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            val selectedValues = chipList
                .filter { it.selected }.joinToString(", ") { it.value }
            Row {
                Button(
                    onClick = {
                        navigateToTherapist(selectedValues)
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Therapist",
                        fontFamily = fontFamily
                    )
                }

                Spacer(modifier = Modifier.width(18.dp))

                Button(
                    onClick = {
                        navigateToContent(selectedValues)
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Content",
                        fontFamily = fontFamily
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { backToHome() }, shape = RoundedCornerShape(16.dp)) {
            Text(
                text = "Finish",
                fontFamily = fontFamily
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PredictScreenPreview() {
    MentalGuardiansTheme {
        PredictScreen("bad")
    }
}