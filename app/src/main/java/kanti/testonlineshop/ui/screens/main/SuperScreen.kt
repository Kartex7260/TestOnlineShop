package kanti.testonlineshop.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
fun SuperScreen(
    startDestination: String? = null
) {
    val navController = rememberNavController()
    Column {
        val context = LocalContext.current
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
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
                CatalogScreen()
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
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    SuperScreen()
}