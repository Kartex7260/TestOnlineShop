package kanti.testonlineshop.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.elementDarkGrey
import kanti.testonlineshop.ui.theme.elementPink

data class BottomNavigationData(
    val label: String,
    val icon: Painter,
    val route: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    items: List<BottomNavigationData> = listOf()
) {
    val unselectColor = MaterialTheme.colors.elementDarkGrey
    val selectColor = MaterialTheme.colors.elementPink

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFE8E9EC)
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        for (item in items) {
            val color = if (item.route == currentRoute) selectColor else unselectColor
            Surface(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    navController.navigate(route = item.route) {
                        popUpTo(route = item.route) {
                            saveState = true
                            inclusive = true
                        }
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 6.dp,
                            bottom = 2.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = item.icon,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.caption1,
                        color = color
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigationBar() {
    BottomNavigationBar(
        modifier = Modifier.fillMaxWidth(),
        items = navItems()
    )
}