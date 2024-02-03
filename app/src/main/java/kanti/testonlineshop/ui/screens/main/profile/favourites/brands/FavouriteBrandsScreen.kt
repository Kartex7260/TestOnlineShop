package kanti.testonlineshop.ui.screens.main.profile.favourites.brands

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kanti.testonlineshop.ui.theme.largeTitle1
import kanti.testonlineshop.ui.theme.textBlack

@Composable
fun FavouriteBrandsScreen(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier.then(
        other = Modifier.fillMaxSize()
    ),
    contentAlignment = Alignment.Center
) {
    Text(
        text = "Brands",
        style = MaterialTheme.typography.largeTitle1,
        color = MaterialTheme.colors.textBlack
    )
}