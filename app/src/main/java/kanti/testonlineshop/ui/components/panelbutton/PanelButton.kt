package kanti.testonlineshop.ui.components.panelbutton

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.button2

@Composable
fun PanelButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    colors: ButtonColors = PanelButtonDefaults.colors(),
    onClick: () -> Unit = {}
) = Button(
    onClick = onClick,
    modifier = modifier
        .then(other = Modifier.height(36.dp)),
    enabled = enabled,
    colors = colors,
    shape = RoundedCornerShape(8.dp),
    elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp
    )
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