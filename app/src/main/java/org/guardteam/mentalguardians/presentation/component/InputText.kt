package org.guardteam.mentalguardians.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.guardteam.mentalguardians.presentation.theme.fontFamily

@Composable
fun InputText(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    minLine: Int = 1,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit = {},
    supportingText: @Composable () -> Unit = {},
    colors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        errorContainerColor = MaterialTheme.colorScheme.background
    )
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        singleLine = singleLine,
        textStyle = TextStyle(
            fontFamily = fontFamily
        ),
        minLines = minLine,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        isError = isError,
        colors = colors,
        label = {
            Text(text = label, fontFamily = fontFamily)
        },
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        modifier = modifier
            .fillMaxWidth()
    )
}