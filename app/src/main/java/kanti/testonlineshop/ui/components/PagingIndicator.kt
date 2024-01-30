package kanti.testonlineshop.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.backgroundPink
import kanti.testonlineshop.ui.theme.elementLightGrey

@Composable
fun PagingIndicator(
    modifier: Modifier = Modifier,
    count: Int = 1,
    select: Int = 0
) = Row(
    modifier = modifier
) {
    repeat(count) {
        Box(
            modifier = Modifier.size(8.dp),
            contentAlignment = Alignment.Center
        ) {
            val color = if (it == select) MaterialTheme.colors.backgroundPink
            else MaterialTheme.colors.elementLightGrey
            val radius = with(LocalDensity.current) { 2.dp.toPx() }
            Canvas(modifier = Modifier) {
                drawCircle(
                    color = color,
                    radius = radius
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPagingIndicator() {
    PagingIndicator(
        count = 4,
        select = 0
    )
}