package kanti.testonlineshop.ui.screens.main.catalog

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.productdetail.ProductDetailScreen

@Composable
fun CatalogRootScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController()
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = context.getString(R.string.nav_main_catalog_root)
) {
    composable(route = context.getString(R.string.nav_main_catalog_root)) {
        CatalogScreen(
            toProductScreen = { productId ->
                navController.navigate(
                    route = "${context.getString(R.string.nav_main_catalog_product)}/$productId"
                )
            }
        )
    }

    val productDetailArg1 = "productId"
    composable(
        route = "${context.getString(R.string.nav_main_catalog_product)}/{$productDetailArg1}",
        arguments = listOf(navArgument(productDetailArg1) { type = NavType.StringType })
    ) {
        ProductDetailScreen(
            navController = navController,
            productId = it.arguments?.getString(productDetailArg1)
        )
    }
}