package kanti.testonlineshop.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.elementBlack

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    tint: Color = MaterialTheme.colors.elementBlack,
    onClick: () -> Unit = {}
) = androidx.compose.material.IconButton(
    onClick = onClick,
    modifier = modifier
        .size(32.dp)
) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = null,
        tint = tint
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconButton() {
    IconButton(
        id = R.drawable.plus
    )
}