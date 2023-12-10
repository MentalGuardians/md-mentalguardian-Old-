package org.guardteam.mentalguardians.presentation.transaction.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun TransactionBottomSheet(
    modifier: Modifier = Modifier,
    psychologistName: String,
    date: String,
    time: String,
    description: String,
    link: String,
    status: String,
    whatsapp: String,
    onClick: () -> Unit
){
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column() {
                Text(
                    text = psychologistName,
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "$date at $time",
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                ClickableText(
                    text = AnnotatedString(whatsapp),
                    onClick = {
                              openWhatsapp(context, whatsapp)
                    },
                    modifier = modifier,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                )
                ClickableText(
                    text = AnnotatedString(link),
                    onClick = {
                        openLink(context, link)
                    },
                    modifier = modifier,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
            Spacer(modifier = modifier.weight(1f))
            val statusColor = when(status){
                "Scheduled" -> Color(0xFFFFA500)
                "Finished" -> Color(0xFF008000)
                "Canceled" -> Color(0xFFFF0000)
                else -> MaterialTheme.colorScheme.primary
            }
            Text(
                text = status,
                color = statusColor
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(text = description)
    }
}

private fun openWhatsapp(context: Context, noTelephone: String){
    var uri = Uri.parse("https://api.whatsapp.com/send?phone=$noTelephone")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}
private fun openLink(context: Context, link: String){
    var uri = Uri.parse(link)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun TransactionBSPreview(){
    MentalGuardiansTheme {
        TransactionBottomSheet(
            psychologistName = "Ihfansyah Pedo",
            date = "22 November 2023",
            time = "20.30",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            link = "https://meet.google.com/tsi/frfb-nzt",
            status = "Scheduled",
            whatsapp ="+6282146804920"
        ) {

        }
    }
}