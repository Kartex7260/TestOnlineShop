package kanti.testonlineshop.ui.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.button1
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String = "",
    contentPadding: PaddingValues = PaddingValues(top = 10.dp, end = 10.dp, bottom = 10.dp),
    onClick: () -> Unit = {}
) = Surface(
    modifier = modifier
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
            enabled = true,
            onClick = onClick
        ),
    color = Color.Transparent
) {
    Text(
        modifier = Modifier
            .padding(contentPadding),
        text = text,
        style = MaterialTheme.typography.button1,
        color = MaterialTheme.colors.textGrey
    )
}

@Preview
@Composable
fun PreviewTextButton() {
    TextButton(
        text = "Test"
    )
}