package kanti.testonlineshop.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.elementPink
import kanti.testonlineshop.ui.theme.elementWhite

@Composable
fun PlusButton(
    modifier: Modifier = Modifier
) = Surface(
    modifier = modifier
        .size(32.dp),
    shape = RoundedCornerShape(
        topStart = 8.dp,
        bottomEnd = 8.dp
    ),
    color = MaterialTheme.colors.elementPink
) {
    Image(
        painter = painterResource(id = R.drawable.plus),
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.elementWhite)
    )
}

@Preview
@Composable
fun PreviewPlusButton() {
    PlusButton()
}