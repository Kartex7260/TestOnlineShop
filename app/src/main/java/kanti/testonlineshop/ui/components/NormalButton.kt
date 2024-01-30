package kanti.testonlineshop.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.backgroundLightPink
import kanti.testonlineshop.ui.theme.backgroundPink
import kanti.testonlineshop.ui.theme.button2
import kanti.testonlineshop.ui.theme.textWhite

@Composable
fun NormalButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = Button(
    onClick = onClick,
    modifier = modifier
        .height(51.dp),
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.backgroundPink,
        contentColor = MaterialTheme.colors.textWhite,
        disabledBackgroundColor = MaterialTheme.colors.backgroundLightPink,
        disabledContentColor = MaterialTheme.colors.textWhite
    ),
    shape = RoundedCornerShape(8.dp)
) {
    Text(
        text = text,
        style = MaterialTheme.typography.button2
    )
}

@Preview
@Composable
private fun PreviewButtonNormal(
    @PreviewParameter(PreviewButtonNormalEnabled::class) enabled: Boolean
) {
    NormalButton(
        text = "It preview text",
        enabled = enabled
    )
}

private class PreviewButtonNormalEnabled : CollectionPreviewParameterProvider<Boolean>(
    listOf(true, false)
)