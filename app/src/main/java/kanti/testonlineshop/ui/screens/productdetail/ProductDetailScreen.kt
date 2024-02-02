package kanti.testonlineshop.ui.screens.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.screens.productdetail.viewmodel.ProductDetailViewModel
import kanti.testonlineshop.ui.theme.elementBlack

@Composable
private fun TopAppBar(
    onBack: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(
                top = 14.dp,
                bottom = 8.dp,
                start = 21.dp,
                end = 14.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            iconId = R.drawable.back_arrow,
            tint = MaterialTheme.colors.elementBlack
        )

        IconButton(
            modifier = Modifier.size(24.dp),
            iconId = R.drawable.share,
            tint = MaterialTheme.colors.elementBlack
        )
    }
}

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = ProductDetailViewModel,
    productId: String? = null
) = Column(
    modifier = modifier
) {
    TopAppBar()

    Spacer(modifier = Modifier.height(16.dp))


}

@Preview
@Composable
fun PreviewProductScreen() {
    ProductDetailScreen(
        modifier = Modifier.fillMaxSize()
    )
}