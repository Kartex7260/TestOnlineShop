package kanti.testonlineshop.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.login.LoginScreen
import kanti.testonlineshop.ui.screens.main.SuperScreen
import kanti.testonlineshop.ui.screens.productdetail.ProductDetailScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = stringResource(id = R.string.nav_login)
    ) {
        composable(
            route = context.getString(R.string.nav_login)
        ) {
            LoginScreen(navController = navController)
        }

        val mainArg1 = "destination"
        composable(
            route = "${context.getString(R.string.nav_main)}/{$mainArg1}",
            arguments = listOf(navArgument(mainArg1) { type = NavType.StringType })
        ) {
            SuperScreen(
                startDestination = it.arguments?.getString(mainArg1),
                superNavController = navController
            )
        }

        val productDetailArg1 = "productId"
        composable(
            route = "${context.getString(R.string.nav_product_detail)}/{$productDetailArg1}",
            arguments = listOf(navArgument(productDetailArg1) { type = NavType.StringType })
        ) {
            ProductDetailScreen(
                productId = it.arguments?.getString(productDetailArg1)
            )
        }
    }
}