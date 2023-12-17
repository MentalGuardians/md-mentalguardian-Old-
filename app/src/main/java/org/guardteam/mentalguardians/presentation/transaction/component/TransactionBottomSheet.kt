package org.guardteam.mentalguardians.presentation.transaction.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
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
import org.guardteam.mentalguardians.utils.toFormat
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun TransactionBottomSheet(
    psychologistName: String,
    date: String,
    time: String,
    method: String,
    status: String,
    onClick: () -> Unit,
    bookDate: String,
    buttonActive: Boolean,
    modifier: Modifier = Modifier,
    link: String? = null,
) {
    val context = LocalContext.current
    val formattedDate = date.toFormat("dd MMMM yyyy")
    val splitBookDate = bookDate.split(" ")
    val formatterBookDate = "${splitBookDate[0].toFormat("dd MMMM yyyy")} ${splitBookDate[1]}"
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            val statusColor = when (status) {
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
        Text(
            text = psychologistName,
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "$formattedDate at $time",
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(text = "$method method")
        link?.let {
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

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Requested at $formatterBookDate",
            fontFamily = fontFamily,
            fontSize = 12.sp,
        )

        if (status == "Scheduled") {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                enabled = buttonActive
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = fontFamily
                )
            }
        }

    }
}

private fun openLink(context: Context, link: String) {
    val uri = Uri.parse(link)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun TransactionBSPreview() {
    MentalGuardiansTheme {
        TransactionBottomSheet(
            psychologistName = "Ihfansyah Pedo",
            date = "2023-12-18",
            time = "20.30",
            link = "https://meet.google.com/tsi/frfb-nzt",
            status = "Scheduled",
            method = "online",
            onClick = {},
            bookDate = "2023-12-17 09:09",
            buttonActive = false
        )
    }
}