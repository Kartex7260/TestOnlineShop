package kanti.testonlineshop.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.backgroundPink
import kanti.testonlineshop.ui.theme.elementLightGrey

@Composable
fun PagingIndicator(
    modifier: Modifier = Modifier,
    count: Int = 1,
    select: Int = 0,
    contentPadding: PaddingValues = PaddingValues(2.dp),
    dotDiameter: Dp = 4.dp,
    spaceBetween: Dp = 4.dp
) = Box(
    modifier = modifier
) {
    Row(
        modifier = Modifier.padding(contentPadding)
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier.size(dotDiameter),
                contentAlignment = Alignment.Center
            ) {
                val color = if (index == select) MaterialTheme.colors.backgroundPink
                else MaterialTheme.colors.elementLightGrey
                val radius = with(LocalDensity.current) { dotDiameter.toPx() / 2 }
                Canvas(modifier = Modifier) {
                    drawCircle(
                        color = color,
                        radius = radius
                    )
                }
            }
            if (index < count - 1) {
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }
    }
}

@Preview
@Composable
fun PreviewPagingIndicator() {
    PagingIndicator(
        count = 4,
        select = 0,
        spaceBetween = 4.dp
    )
}