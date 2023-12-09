package org.guardteam.mentalguardians.presentation.history.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
fun DaftarHistory(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
){
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
        )
        {
            Text(
                text = date,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Spacer(modifier = modifier.weight(1F))
            Text(
                text = time,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Blue,
                modifier = modifier.size(28.dp)
            )
        }
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()

        )
    }
}
@Preview(showBackground = true)
@Composable
fun DaftarHistoryPreview(){
    MentalGuardiansTheme {
        DaftarHistory(date = "22 November 2023", time = "20:30 PM")
    }
}