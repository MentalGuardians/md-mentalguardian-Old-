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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
            .padding(9.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column(
            ) {
                Text(text = "$name")
                Text(text = "$date at $time")
            }
            Spacer(modifier = modifier.weight(1f))
            Text(text = "$status", )
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