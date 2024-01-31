package kanti.testonlineshop.ui.components.edittext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

@Composable
fun PhoneEditText(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    enabled: Boolean = true,
    isError: Boolean = false,
    mask: String = "+7 XXX-XXX-XX-XX",
    maskSymbol: Char = 'X',
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    placeholder: String = ""
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    EditText(
        modifier = modifier
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        value = run {
            if (value.firstOrNull() == '7' || value.firstOrNull() == '8')
                return@run ""
            value.filter { char -> numbers.contains(char) }
        },
        onValueChanged = { newPhone ->
            onValueChanged(newPhone.take(mask.count { it == maskSymbol }))
        },
        enabled = enabled,
        isError = isError,
        visualTransformation = PhoneVisualTransformation(mask, maskSymbol),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = if (isFocused) mask else placeholder
    )
}

data class PhoneVisualTransformation(val mask: String, val maskSymbol: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskSymbol }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskSymbol) {
                    val nextDigitIndex = mask.indexOf(maskSymbol, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskSymbol))
    }
}

private class PhoneOffsetMapper(val mask: String, val maskSymbol: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != maskSymbol) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != maskSymbol }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhoneEditText() {
    var text by remember {
        mutableStateOf("")
    }
    val placeholder = "Phone number"

    Column(modifier = Modifier.background(Color.White)) {
        PhoneEditText(
            modifier = Modifier.padding(16.dp),
            enabled = true,
            isError = false,
            value = text,
            onValueChanged = { text = it },
            placeholder = placeholder
        )

        PhoneEditText(
            modifier = Modifier.padding(16.dp),
            enabled = true,
            isError = true,
            value = text,
            onValueChanged = { text = it },
            placeholder = placeholder
        )

        PhoneEditText(
            modifier = Modifier.padding(16.dp),
            enabled = false,
            isError = false,
            value = text,
            onValueChanged = { text = it },
            placeholder = placeholder
        )

        PhoneEditText(
            modifier = Modifier.padding(16.dp),
            enabled = false,
            isError = true,
            value = text,
            onValueChanged = { text = it },
            placeholder = placeholder
        )
    }
}