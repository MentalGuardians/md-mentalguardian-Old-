package org.guardteam.mentalguardians.presentation.profile.extra

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.guardteam.mentalguardians.presentation.common.InputTextState
import org.guardteam.mentalguardians.presentation.component.InputText
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.utils.isInvalid
import org.guardteam.mentalguardians.utils.isValidEmail

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    backToEdit: () -> Unit = {},
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {
    var nameEdit by remember { mutableStateOf(InputTextState()) }
    var emailEdit by remember { mutableStateOf(InputTextState()) }
    var telephoneEdit by remember {
        mutableStateOf(InputTextState())
    }
    var addressEdit by remember { mutableStateOf(InputTextState()) }
    var passwordEdit by remember {
        mutableStateOf(InputTextState())
    }
    var passwordVisibility by remember { mutableStateOf(false) }

    val editProfile by editProfileViewModel.editProfile.collectAsState()

    editProfile.let {
        val context = LocalContext.current
        if (!editProfile.hasBeenHandled) {
            when (val unhandled = editProfile.getContentIfNotHandled()) {
                is Result.Error -> {
                    Toast.makeText(context, unhandled.error, Toast.LENGTH_SHORT).show()
                }

                is Result.Success -> {
                    Toast.makeText(context, unhandled.data.message, Toast.LENGTH_SHORT).show()
                    backToEdit()
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
                if (nameEdit.isError) {
                    Text(text = "Username cannot be empty", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 12.dp)
        )

        InputText(
            value = passwordEdit.value,
            onChange = { newPw ->
                passwordEdit = passwordEdit.copy(
                    value = newPw,
                    isError = newPw.length <= 6
                )
            },
            label = "Enter New Password",
            isError = passwordEdit.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
                val desc = if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = icon, contentDescription = desc)
                }
            },
            supportingText = {
                if (passwordEdit.isError) {
                    Text(text = "Password at least 6 characters", fontFamily = fontFamily)
                }
            },
            modifier = Modifier.padding(top = 8.dp)
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
                if (emailEdit.isError) {
                    Text(text = "Email not valid", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 3.dp)
        )
        InputText(
            value = telephoneEdit.value,
            onChange = { newTelephone ->
                val formattedTelephone = if (!newTelephone.startsWith("62")) {
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
                if (telephoneEdit.isError) {
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
                if (nameEdit.isError) {
                    Text(text = "Cannot be empty", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 12.dp)
        )

        Spacer(modifier = modifier.weight(1f))
        Spacer(modifier = modifier.height(24.dp))
        Button(
            enabled = editProfile.peekContent() !is Result.Loading,
            onClick = {
                when {
                    nameEdit.isInvalid() -> nameEdit = nameEdit.copy(isError = true)
                    emailEdit.isInvalid() -> emailEdit = emailEdit.copy(isError = true)
                    passwordEdit.isInvalid() -> passwordEdit = passwordEdit.copy(isError = true)
                    telephoneEdit.isInvalid() -> telephoneEdit = telephoneEdit.copy(isError = true)
                    addressEdit.isInvalid() -> addressEdit = addressEdit.copy(isError = true)
                    else -> editProfileViewModel.editProfile(
                        username = nameEdit.value,
                        email = emailEdit.value,
                        password = passwordEdit.value,
                        phone = telephoneEdit.value,
                        address = addressEdit.value
                    )
                }
            },
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
fun PreviewEdit() {
    MaterialTheme {
        EditProfile()
    }
}