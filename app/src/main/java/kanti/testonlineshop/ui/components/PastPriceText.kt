package kanti.testonlineshop.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.textBlack

@Composable
fun PastPriceText(
    modifier: Modifier = Modifier,
    text: String = "",
    color: Color = MaterialTheme.colors.textBlack
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
) {
    var textWidth by remember {
        mutableIntStateOf(0)
    }
    Text(
        text = text,
        style = MaterialTheme.typography.caption1,
        color = color,
        modifier = Modifier
            .onSizeChanged {
                textWidth = it.width
            }
    )

    val lineLength = textWidth.toFloat()
    val lineThickness = with(LocalDensity.current) { 1.dp.toPx() }
    Canvas(
        modifier = Modifier
            .rotate(-7.68f)
    ) {
        drawLine(
            color = color,
            start = Offset(-(lineLength / 2), 0f),
            end = Offset(lineLength / 2, 0f),
            strokeWidth = lineThickness
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPastPriceTest() {
    PastPriceText(
        text = "1 000"
    )
}