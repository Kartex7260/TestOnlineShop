package kanti.testonlineshop.ui.components.edittext

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.elementBlack
import kanti.testonlineshop.ui.theme.elementDarkGrey
import kanti.testonlineshop.ui.theme.elementOrange
import kanti.testonlineshop.ui.theme.placeholder
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    placeholder: String = ""
) {
    val clearIcon = @Composable {
        IconButton(
            enabled = enabled,
            onClick = { onValueChanged("") }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.clear),
                contentDescription = null
            )
        }
    }

    val disabledAlpha = .4f
    val border = BorderStroke(
        color = if (enabled) MaterialTheme.colors.elementOrange
        else MaterialTheme.colors.elementOrange.copy(alpha = disabledAlpha),
        width = 1.dp
    )
    Surface(
        modifier = modifier,
        color = if (enabled) MaterialTheme.colors.backgroundLightGrey
        else MaterialTheme.colors.backgroundLightGrey.copy(alpha = disabledAlpha),
        shape = RoundedCornerShape(8.dp),
        border = if (isError) border else null
    ) {
        TextField(
            isError = true,
            enabled = enabled,
            value = value,
            onValueChange = onValueChanged,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.placeholder
                )
            },
            trailingIcon = if (value.isNotEmpty()) clearIcon else null,
            textStyle = MaterialTheme.typography.placeholder,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.textBlack,
                disabledTextColor = MaterialTheme.colors.textBlack.copy(alpha = disabledAlpha),
                backgroundColor = Color.Transparent,

                placeholderColor = MaterialTheme.colors.textGrey,
                disabledPlaceholderColor = MaterialTheme.colors.textGrey.copy(alpha = disabledAlpha),

                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                cursorColor = MaterialTheme.colors.elementBlack,
                errorCursorColor = MaterialTheme.colors.elementBlack,

                trailingIconColor = MaterialTheme.colors.elementDarkGrey,
                disabledTrailingIconColor = MaterialTheme.colors.elementDarkGrey.copy(alpha = disabledAlpha),
                errorTrailingIconColor = MaterialTheme.colors.elementDarkGrey
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEditText() {
    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.background(Color.White)) {
        EditText(
            modifier = Modifier.padding(16.dp),
            enabled = true,
            isError = false,
            value = text,
            onValueChanged = { text = it },
            placeholder = "Label"
        )

        EditText(
            modifier = Modifier.padding(16.dp),
            enabled = true,
            isError = true,
            value = text,
            onValueChanged = { text = it },
            placeholder = "Label"
        )

        EditText(
            modifier = Modifier.padding(16.dp),
            enabled = false,
            isError = false,
            value = text,
            onValueChanged = { text = it },
            placeholder = "Label"
        )

        EditText(
            modifier = Modifier.padding(16.dp),
            enabled = false,
            isError = true,
            value = text,
            onValueChanged = { text = it },
            placeholder = "Label"
        )
    }
}