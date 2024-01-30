package kanti.testonlineshop.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.element
import kanti.testonlineshop.ui.theme.elementPink
import kanti.testonlineshop.ui.theme.textWhite

@Composable
fun DiscountTag(
    modifier: Modifier = Modifier,
    text: String = ""
) = Surface(
    modifier = modifier,
    color = MaterialTheme.colors.elementPink,
    shape = RoundedCornerShape(4.dp)
) {
    Box(
        modifier = Modifier
            .defaultMinSize(34.dp, 16.dp)
            .padding(
                bottom = 2.dp,
                start = 6.dp,
                end = 6.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "-$text%",
            style = MaterialTheme.typography.element,
            color = MaterialTheme.colors.textWhite
        )
    }
}

@Preview
@Composable
private fun PreviewDiscountTag() {
    DiscountTag(
        text = "45"
    )
}