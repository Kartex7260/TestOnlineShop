package kanti.testonlineshop.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.login.LoginScreen

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
            LoginScreen()
        }
    }
}