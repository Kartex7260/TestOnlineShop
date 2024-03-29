package kanti.testonlineshop.ui.screens.main.profile.favourites.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kanti.testonlineshop.ui.components.product.ProductsGrid
import kanti.testonlineshop.ui.screens.main.profile.favourites.products.viewmodel.FavouriteProductsViewModel
import kanti.testonlineshop.ui.screens.main.profile.favourites.products.viewmodel.FavouriteProductsViewModelImpl

@Composable
fun FavouriteProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteProductsViewModel = hiltViewModel<FavouriteProductsViewModelImpl>(),
    toProductScreen: (String) -> Unit = {},
) = ProductsGrid(
    modifier = modifier,
    items = viewModel.favouriteProducts.collectAsState().value,
    getImages = { viewModel.getImages(it) },
    setFavourite = viewModel::setFavourite,
    toProductScreen = toProductScreen
)

@Preview
@Composable
fun PreviewFavouritesProductsScreen() {
    FavouriteProductsScreen(
        viewModel = FavouriteProductsViewModel
    )
}