package org.guardteam.mentalguardians.presentation.profile.extra

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.guardteam.mentalguardians.common.state.InputTextState
import org.guardteam.mentalguardians.common.utils.isValidEmail
import org.guardteam.mentalguardians.presentation.component.InputText
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun EditProfile(
    modifier: Modifier = Modifier
){
    EditComponent {

    }
}

@Composable
fun EditComponent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){
    var nameEdit by remember { mutableStateOf(InputTextState()) }
    var emailEdit by remember { mutableStateOf(InputTextState()) }
    var telephoneEdit by remember {
        mutableStateOf(InputTextState())
    }
    var addressEdit by remember { mutableStateOf(InputTextState()) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = modifier
                .padding(vertical = 12.dp)
                .clickable { onBackClick() }
        )
        Text(
            text = "Edit Profile",
            fontFamily = fontFamily,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Personal Information",
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            fontFamily = fontFamily
        )

        InputText(
            value = nameEdit.value,
            onChange = { newName ->
                nameEdit = nameEdit.copy(
                    value = newName,
                    isError = newName.isEmpty()
                )
            },
            label = "Enter New Username",
            isError = nameEdit.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if (nameEdit.isError){
                    Text(text = "Username cannot be empty", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 12.dp)
        )

        InputText(
            value = emailEdit.value,
            onChange = { newEmail ->
                emailEdit = emailEdit.copy(
                    value = newEmail,
                    isError = !newEmail.isValidEmail()
                )
            },
            label = "Enter New Email",
            isError = emailEdit.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            supportingText = {
                if (emailEdit.isError){
                    Text(text = "Email not valid", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 3.dp)
        )
        InputText(
            value = telephoneEdit.value,
            onChange = {newTelephone ->
                val formattedTelephone = if (!newTelephone.startsWith("62")){
                    "62$newTelephone"
                } else {
                    newTelephone
                }
                telephoneEdit = telephoneEdit.copy(
                    value = formattedTelephone,
                    isError = formattedTelephone.length < 12
                )
            },
            label = "Enter New Telephone",
            isError = telephoneEdit.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            supportingText = {
                if (telephoneEdit.isError){
                    Text(text = "Telephone not valid", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 3.dp)
        )
        InputText(
            value = addressEdit.value,
            onChange = { newAddress ->
                addressEdit = addressEdit.copy(
                    value = newAddress,
                    isError = newAddress.isEmpty()
                )
            },
            label = "Enter New Address",
            isError = nameEdit.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if (nameEdit.isError){
                    Text(text = "Cannot be empty", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 12.dp)
        )

        Spacer(modifier = modifier.weight(1f))
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(
                text = "Save Edit",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEdit(){
    MaterialTheme {
        EditComponent {

        }
    }
}