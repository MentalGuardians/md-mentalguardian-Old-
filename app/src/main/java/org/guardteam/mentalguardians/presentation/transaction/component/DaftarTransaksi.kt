package org.guardteam.mentalguardians.presentation.transaction.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun DaftarTransaksi(
    name: String,
    date: String,
    time: String,
    status: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Column(
            ) {
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$date at $time",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = status,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = modifier.padding(start = 10.dp)
            )
        }

        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarTransaksiPreview(){
    MentalGuardiansTheme {
        DaftarTransaksi(name = "Ihfansyah Pedo", date = "22 November 2003", time = "20.30", status = "Scheduled")
    }
}