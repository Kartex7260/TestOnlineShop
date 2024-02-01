package kanti.testonlineshop.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.main.cart.CartScreen
import kanti.testonlineshop.ui.screens.main.catalog.CatalogScreen
import kanti.testonlineshop.ui.screens.main.main.MainScreen
import kanti.testonlineshop.ui.screens.main.profile.ProfileScreen
import kanti.testonlineshop.ui.screens.main.promo.PromoScreen

@Composable
fun navItems() = listOf(
    BottomNavigationData(
        label = stringResource(id = R.string.main_main),
        icon = painterResource(id = R.drawable.home),
        route = stringResource(id = R.string.nav_main_main)
    ),
    BottomNavigationData(
        label = stringResource(id = R.string.main_catalog),
        icon = painterResource(id = R.drawable.catalog),
        route = stringResource(id = R.string.nav_main_catalog)
    ),
    BottomNavigationData(
        label = stringResource(id = R.string.main_cart),
        icon = painterResource(id = R.drawable.cart),
        route = stringResource(id = R.string.nav_main_cart)
    ),
    BottomNavigationData(
        label = stringResource(id = R.string.main_promo),
        icon = painterResource(id = R.drawable.promo),
        route = stringResource(id = R.string.nav_main_promo)
    ),
    BottomNavigationData(
        label = stringResource(id = R.string.main_profile),
        icon = painterResource(id = R.drawable.profile),
        route = stringResource(id = R.string.nav_main_profile)
    )
)

@Composable
fun SuperScreen(
    startDestination: String? = null,
    superNavController: NavController = rememberNavController()
) {
    val currentNavController = rememberNavController()
    Column {
        val context = LocalContext.current
        val navBottomItems = navItems()
        NavHost(
            modifier = Modifier.weight(1f),
            navController = currentNavController,
            startDestination = startDestination ?: stringResource(id = R.string.nav_main_main)
        ) {
            composable(
                route = context.getString(R.string.nav_main_main)
            ) {
                MainScreen()
            }

            composable(
                route = context.getString(R.string.nav_main_catalog)
            ) {
                CatalogScreen(
                    toProductScreen = { productId ->
                        superNavController.navigate(
                            route = "${context.getString(R.string.nav_product_detail)}/$productId"
                        )
                    }
                )
            }

            composable(
                route = context.getString(R.string.nav_main_cart)
            ) {
                CartScreen()
            }

            composable(
                route = context.getString(R.string.nav_main_promo)
            ) {
                PromoScreen()
            }

            composable(
                route = context.getString(R.string.nav_main_profile)
            ) {
                ProfileScreen()
            }
        }
        BottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            navController = currentNavController,
            items = navBottomItems
        )
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    SuperScreen()
}