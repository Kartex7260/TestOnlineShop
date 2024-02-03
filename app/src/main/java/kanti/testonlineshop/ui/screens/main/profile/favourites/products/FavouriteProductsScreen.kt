package kanti.testonlineshop.ui.screens.main.profile.favourites.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kanti.testonlineshop.ui.components.product.ProductsGrid
import kanti.testonlineshop.ui.screens.main.profile.favourites.products.viewmodel.FavouriteProductsViewModel

@Composable
fun FavouriteProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteProductsViewModel = FavouriteProductsViewModel
) = ProductsGrid(
    modifier = modifier,
    items = viewModel.favouriteProducts.collectAsState().value,
    getImages = { viewModel.getImages(it) }
)

@Preview
@Composable
fun PreviewFavouritesProductsScreen() {
    FavouriteProductsScreen(
        viewModel = FavouriteProductsViewModel
    )
}