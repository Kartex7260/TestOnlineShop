package kanti.testonlineshop.ui.components.panelbutton

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
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.backgroundWhite
import kanti.testonlineshop.ui.theme.button2
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun PanelButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = Button(
    onClick = onClick,
    modifier = modifier
        .height(36.dp),
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.backgroundWhite,
        contentColor = MaterialTheme.colors.textBlack,
        disabledBackgroundColor = MaterialTheme.colors.backgroundLightGrey,
        disabledContentColor = MaterialTheme.colors.textGrey
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
fun PreviewPanelButton(
    @PreviewParameter(PreviewPanelButtonEnable::class) enabled: Boolean
) {
    PanelButton(
        text = "Label",
        enabled = enabled
    )
}

class PreviewPanelButtonEnable : CollectionPreviewParameterProvider<Boolean>(listOf(true, false))