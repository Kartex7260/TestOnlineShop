package kanti.testonlineshop.ui.components.product

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.theme.elementPink

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    favourite: Boolean = false,
    onChangeFavourite: (Boolean) -> Unit = {}
) = IconButton(
    modifier = modifier,
    iconId = if (favourite) R.drawable.heart_full
    else R.drawable.heart_empty,
    tint = MaterialTheme.colors.elementPink,
    onClick = { onChangeFavourite(!favourite) }
)

@Preview
@Composable
private fun PreviewFavouriteButton() {
    var favourite by remember { mutableStateOf(false) }
    FavouriteButton(
        favourite = favourite,
        onChangeFavourite = { favourite = it }
    )
}