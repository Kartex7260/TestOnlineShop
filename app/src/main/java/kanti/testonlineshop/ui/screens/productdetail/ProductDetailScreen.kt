package kanti.testonlineshop.ui.screens.productdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kanti.testonlineshop.ui.screens.productdetail.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = ProductDetailViewModel,
    productId: String? = null
) {
    Text(text = productId ?: "null")
}

@Preview
@Composable
fun PreviewProductScreen() {
    ProductDetailScreen(
        modifier = Modifier.fillMaxSize()
    )
}