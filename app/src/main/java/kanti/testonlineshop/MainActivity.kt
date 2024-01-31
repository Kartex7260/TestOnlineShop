package kanti.testonlineshop

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kanti.testonlineshop.ui.screens.login.NavGraph
import kanti.testonlineshop.ui.theme.TestOnlineShopTheme
import kanti.testonlineshop.ui.theme.backgroundWhite

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(this)
        }
    }
}

@Composable
fun Content(
    context: Context = LocalContext.current
) {
    TestOnlineShopTheme {
        Surface(
            color = MaterialTheme.colors.backgroundWhite
        ) {
            NavGraph(
                modifier = Modifier.fillMaxSize(),
                context = context
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Content()
}