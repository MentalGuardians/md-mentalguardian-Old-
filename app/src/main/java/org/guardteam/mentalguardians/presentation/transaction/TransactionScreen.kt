package org.guardteam.mentalguardians.presentation.transaction


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.presentation.transaction.component.DaftarTransaksi
import org.guardteam.mentalguardians.presentation.transaction.data.DataTransaction

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Transaction",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Text(
            text = "Your Transaction",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ){
               items(DataTransaction.dataTransaction, key ={it.id}) {
                   DaftarTransaksi(
                       name = it.name,
                       date = it.date,
                       time = it.time,
                       status = it.status,
                       modifier = Modifier.clickable {
                       }
                   )
               }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview(){
    TransactionScreen()
}