package kanti.testonlineshop.ui.screens.main.profile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.main.profile.favourites.FavouritesScreen
import kanti.testonlineshop.ui.screens.productdetail.ProductDetailScreen

@Composable
fun ProfileRootScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
    logout: () -> Unit = {}
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = context.getString(R.string.nav_main_profile_root)
) {
    composable(
        route = context.getString(R.string.nav_main_profile_root)
    ) {
        ProfileScreen(
            toFavourite = {
                navController.navigate(
                    route = context.getString(R.string.nav_main_profile_favourite)
                )
            },
            logout = logout
        )
    }

    composable(
        route = context.getString(R.string.nav_main_profile_favourite)
    ) {
        FavouritesScreen(
            toProductScreen = { navController.navigate(route = context.getString(R.string.nav_main_profile_product) + "/$it") },
            back = { navController.popBackStack() }
        )
    }

    val profileArg1 = "productId"
    composable(
        route = "${context.getString(R.string.nav_main_profile_product)}/{$profileArg1}",
        arguments = listOf(navArgument(name = profileArg1) { type = NavType.StringType })
    ) {
        ProductDetailScreen(
            navController = navController,
            productId = it.arguments?.getString(profileArg1)
        )
    }
}

@Preview
@Composable
fun PreviewProfileRootScreen() {
    ProfileRootScreen()
}