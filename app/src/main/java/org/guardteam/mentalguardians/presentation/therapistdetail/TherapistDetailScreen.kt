package org.guardteam.mentalguardians.presentation.therapistdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun TherapistDetailScreen(
    therapistId: Int,
    modifier: Modifier = Modifier
) {
    val therapist: Therapist? = DataDummy.therapistData.find { it.id == therapistId }
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    therapist?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.therapist_profile),
                    contentDescription = "Therapist Profil",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .height(75.dp)
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = it.name,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TherapistDetailScreenPreview() {
    MentalGuardiansTheme {
        TherapistDetailScreen(therapistId = 1)
    }
}