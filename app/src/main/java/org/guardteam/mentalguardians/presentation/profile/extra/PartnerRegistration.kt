package org.guardteam.mentalguardians.presentation.profile.extra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.guardteam.mentalguardians.presentation.common.InputTextState
import org.guardteam.mentalguardians.presentation.component.InputText
import org.guardteam.mentalguardians.presentation.theme.MentalGuardiansTheme
import org.guardteam.mentalguardians.presentation.theme.fontFamily
import org.guardteam.mentalguardians.utils.isValidEmail

@Composable
fun PartnerRegistration(
    modifier: Modifier = Modifier,
) {
    PartnerRegistrationComponent(modifier = modifier)
}

@Composable
fun PartnerRegistrationComponent(
    modifier: Modifier = Modifier,
) {
    var namePartner by remember {
        mutableStateOf(InputTextState())
    }
    var emailPartner by remember {
        mutableStateOf(InputTextState())
    }
    var phonePartner by remember {
        mutableStateOf(InputTextState())
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Partner Registration",
            fontFamily = fontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "If you are interested in partnering with us in the field of mental health, \nplease fill out the form below.",
            fontFamily = fontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )

        InputText(
            value = namePartner.value,
            onChange = { newValue ->
                namePartner = namePartner.copy(
                    value = newValue,
                    isError = newValue.isEmpty()
                )
            },
            label = "Enter Your Name",
            isError = namePartner.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if (namePartner.isError) {
                    Text(text = "Cannot be empty", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 16.dp)
        )
        InputText(
            value = emailPartner.value,
            onChange = { newValue ->
                emailPartner = emailPartner.copy(
                    value = newValue,
                    isError = !newValue.isValidEmail()
                )
            },
            label = "Enter your email",
            isError = emailPartner.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            supportingText = {
                if (emailPartner.isError) {
                    Text(text = "Email not valid", fontFamily = fontFamily)
                }
            },
            modifier = Modifier.padding(top = 3.dp)
        )
        InputText(
            value = phonePartner.value,
            onChange = { newTelephone ->
                val formattedTelephone = if (!newTelephone.startsWith("62")) {
                    "62$newTelephone"
                } else {
                    newTelephone
                }
                phonePartner = phonePartner.copy(
                    value = formattedTelephone,
                    isError = formattedTelephone.length < 12
                )
            },
            label = "Enter New Telephone",
            isError = phonePartner.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            supportingText = {
                if (phonePartner.isError) {
                    Text(text = "Telephone not valid", fontFamily = fontFamily)
                }
            },
            modifier = modifier.padding(top = 3.dp)
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
                text = "Registration",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPartner() {
    MentalGuardiansTheme {
        PartnerRegistration()
    }
}