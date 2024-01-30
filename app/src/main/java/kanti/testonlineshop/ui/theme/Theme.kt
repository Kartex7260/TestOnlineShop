package kanti.testonlineshop.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val colors = lightColors()

val Colors.backgroundWhite: Color get () = White
val Colors.backgroundLightGrey: Color get() = LightGrey
val Colors.backgroundPink: Color get() = Pink
val Colors.backgroundLightPink: Color get() = LightPink

val Colors.textPink: Color get() = Pink
val Colors.textLightPink: Color get() = LightPink
val Colors.textOrange: Color get() = Orange
val Colors.textWhite: Color get() = White
val Colors.textGrey: Color get() = Grey
val Colors.textDarkGrey: Color get() = DarkGrey
val Colors.textBlack: Color get() = Black

val Colors.elementPink: Color get() = Pink
val Colors.elementOrange: Color get() = Orange
val Colors.elementWhite: Color get() = White
val Colors.elementLightGrey: Color get() = ElementsLightGrey
val Colors.elementDarkBlue: Color get() = DarkBlue
val Colors.elementDarkGrey: Color get() = DarkGrey
val Colors.elementBlack: Color get() = Black

    @Composable
fun TestOnlineShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}