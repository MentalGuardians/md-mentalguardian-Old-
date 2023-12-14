package org.guardteam.mentalguardians.presentation.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.R
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ProfileComponent(
    modifier: Modifier = Modifier,
    username: String,
    account: String,
) {
    Column {
        Row {
            Column {
                Text(
                    text = username,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    fontFamily = fontFamily
                )
                Text(
                    text = account,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    fontFamily = fontFamily
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.profile_sample),
                contentDescription = "Profile",
                modifier = modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    MentalGuardiansTheme {
        ProfileComponent(
            username = "Ihfansyah Pedo",
            account = "ihfansyahpedo9@gmail.com")
    }
}