package kanti.testonlineshop.ui.screens.main.profile.favourites

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.TabRow
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.screens.main.profile.favourites.brands.FavouriteBrandsScreen
import kanti.testonlineshop.ui.screens.main.profile.favourites.products.FavouriteProductsScreen
import kanti.testonlineshop.ui.theme.elementBlack
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.title1

private data class TabItem(
    val label: String,
    val route: String
)

private fun tabs(context: Context) = listOf(
    TabItem(
        label = context.getString(R.string.favourites_products),
        route = context.getString(R.string.nav_favourites_products)
    ),
    TabItem(
        label = context.getString(R.string.favourites_brands),
        route = context.getString(R.string.nav_favourites_brands)
    )
)

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    back: () -> Unit = {}
) = Row(
    modifier = modifier
        .height(46.dp)
) {
    Row(
        modifier = Modifier
            .padding(
                start = 21.dp,
                top = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .size(24.dp),
            iconId = R.drawable.back_arrow,
            tint = MaterialTheme.colors.elementBlack,
            onClick = back
        )
        Spacer(modifier = Modifier.width(28.dp))
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.profile_favourites),
            style = MaterialTheme.typography.title1,
            color = MaterialTheme.colors.textBlack
        )
    }
}

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    back: () -> Unit = {},
    toProductScreen: (String) -> Unit = {}
) = Column(
    modifier = modifier
) {
    val context = LocalContext.current

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        back = back
    )
    Spacer(modifier = Modifier.height(4.dp))

    val tabs = remember { tabs(context = context) }
    val navController = rememberNavController()
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route ?: stringResource(
            id = R.string.nav_favourites_products
        )
    TabRow(
        modifier = Modifier
            .height(42.dp)
            .padding(horizontal = 16.dp),
        items = tabs.map { it.label },
        selected = tabs.indexOfFirst { it.route == currentRoute },
        onSelected = {
            val route = tabs[it].route
            if (route == currentRoute)
                return@TabRow
            navController.navigate(route = route) {
                popUpTo(route = route) {
                    inclusive = true
                    saveState = true
                }
            }
        }
    )
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = stringResource(id = R.string.nav_favourites_products)
    ) {
        composable(route = context.getString(R.string.nav_favourites_products)) {
            FavouriteProductsScreen(
                modifier = Modifier.fillMaxSize(),
                toProductScreen = toProductScreen
            )
        }

        composable(route = context.getString(R.string.nav_favourites_brands)) {
            FavouriteBrandsScreen()
        }
    }
}

@Preview
@Composable
fun PreviewFavouriteScreen() {
    FavouritesScreen()
}