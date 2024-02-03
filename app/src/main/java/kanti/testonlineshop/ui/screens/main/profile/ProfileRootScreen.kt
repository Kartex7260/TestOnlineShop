package kanti.testonlineshop.ui.screens.main.profile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R

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
    }

    composable(
        route = context.getString(R.string.nav_main_profile_product)
    ) {
    }
}

@Preview
@Composable
fun PreviewProfileRootScreen() {
    ProfileRootScreen()
}