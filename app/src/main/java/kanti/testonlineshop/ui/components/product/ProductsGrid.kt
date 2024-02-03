package kanti.testonlineshop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductsGrid(
    modifier: Modifier = Modifier,
    items: List<Product>,
    getImages: (String) -> Flow<List<Any>>,
    setFavourite: (String, Boolean) -> Unit = { _, _ -> },
    toProductScreen: (String) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(16.dp),
    inputs: Array<Any?> = arrayOf(null)
) {
    val lazyGridState = rememberSaveable(
        inputs = inputs,
        saver = LazyGridState.Saver
    ) { LazyGridState(0, 0) }
    LazyVerticalGrid(
        modifier = modifier,
        state = lazyGridState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(items = items) { product ->
            key(product.id) {
                val images by getImages(product.id).collectAsState(initial = listOf())
                ProductCard(
                    product = product,
                    images = images,
                    onFavouriteClick = { setFavourite(product.id, it) },
                    onClick = { toProductScreen(product.id) }
                )
            }
        }
    }
}