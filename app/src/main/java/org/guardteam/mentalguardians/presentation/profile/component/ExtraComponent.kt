package org.guardteam.mentalguardians.presentation.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ExtraComponent(
    modifier: Modifier = Modifier,
    onClickProfile: () -> Unit = {},
    onClickContent: () -> Unit = {},
    onClickTherapist: () -> Unit = {},
    onClickRegistration: () -> Unit ={}
){
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        Column {
            Row (
                modifier = modifier
                    .padding(10.dp)
                    .clickable { onClickProfile() }
            ){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Edit Profile",
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Row (
                modifier = modifier
                    .padding(10.dp)
                    .clickable { onClickContent() }
            ){
                Icon(
                    imageVector = Icons.Default.Article,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Favorite Content",
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Row (
                modifier = modifier
                    .padding(10.dp)
                    .clickable { onClickTherapist() }
            ){
                Icon(
                    imageVector = Icons.Default.Psychology,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Favorite Therapist",
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Row (
                modifier = modifier
                    .padding(10.dp)
                    .clickable { onClickRegistration() }
            ){
                Icon(
                    imageVector = Icons.Default.Work,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Partner registration",
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExtra(){
    MaterialTheme {
        ExtraComponent()
    }
}