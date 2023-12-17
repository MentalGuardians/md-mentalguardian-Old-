package org.guardteam.mentalguardians.presentation.profile


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.common.utils.DataDummy
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.presentation.profile.component.ExtraComponent
import org.guardteam.mentalguardians.presentation.profile.component.ProfileComponent
import org.guardteam.mentalguardians.presentation.profile.component.ProfileDetail
import org.guardteam.mentalguardians.presentation.profile.data.Profile
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateToEdit: () -> Unit = {},
    navigateToContent: () -> Unit = {},
    navigateToTherapist: () -> Unit = {},
    navigateToRegistration: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profile by viewModel.profile.collectAsState()
    var profileData by remember {
        mutableStateOf(Profile(
            "",
            "",
            "",
            "",
            ""
        ))
    }
    when(val resultData = profile){
        is Result.Success -> {
            profileData = resultData.data
        }

        else -> {}
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(24.dp)
    ) {

        ProfileComponent(username = profileData.username, account = profileData.account)
        Card (
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            border = BorderStroke(1.dp,MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
                .clickable {}
        ) {
            Row(
                modifier = modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Help",
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Text(
            text = "Account Detail",
            fontSize = 12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        )
        ProfileDetail(username = profileData.username, address = profileData.address, telephone = profileData.telephone)
        ExtraComponent(
            onClickProfile = navigateToEdit,
            onClickContent = navigateToContent,
            onClickTherapist = navigateToTherapist,
            onClickRegistration = navigateToRegistration
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview(){
    MentalGuardiansTheme {
        ProfileScreen()
    }
}