package kanti.testonlineshop.ui.screens.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.components.normalbutton.PriceNormalButton
import kanti.testonlineshop.ui.components.product.ProductDetailPanel
import kanti.testonlineshop.ui.screens.productdetail.viewmodel.ProductDetailViewModel
import kanti.testonlineshop.ui.screens.productdetail.viewmodel.ProductDetailViewModelImpl
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
            tint = MaterialTheme.colors.elementBlack,
            onClick = onBack
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
    navController: NavController = rememberNavController(),
    viewModel: ProductDetailViewModel = hiltViewModel<ProductDetailViewModelImpl>(),
    productId: String? = null
) = Column(
    modifier = modifier
) {
    LifecycleStartEffect {
        if (productId != null)
            viewModel.loadProduct(productId)
        onStopOrDispose {  }
    }

    TopAppBar(
        onBack = { navController.popBackStack() }
    )

    Box {
        val product by viewModel.product.collectAsState()
        val images by viewModel.images.collectAsState()

        var paddingBottom by remember { mutableStateOf(0.dp) }
        val verticalPadding = 8.dp
        ProductDetailPanel(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingBottom + verticalPadding)
                .padding(paddingValues = PaddingValues()),
            product = product,
            images = images,
            onChangeFavourite = { favourite ->
                viewModel.changeFavourite(product.id, favourite)
            }
        )
        val destiny = LocalDensity.current
        PriceNormalButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = verticalPadding,
                    horizontal = 16.dp
                )
                .onGloballyPositioned {
                    paddingBottom = with(destiny) {
                        it.size.height.toDp()
                    }
                },
            price = product.price,
            rightText = stringResource(id = R.string.product_detail_add_to_cart)
        )
    }
}

@Preview
@Composable
fun PreviewProductScreen() {
    ProductDetailScreen(
        modifier = Modifier.fillMaxSize()
    )
}