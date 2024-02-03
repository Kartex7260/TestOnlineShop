package kanti.testonlineshop.ui.components.panelbutton

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.backgroundWhite
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey

object PanelButtonDefaults {

    @Composable
    fun colors(
        backgroundColor: Color = MaterialTheme.colors.backgroundWhite,
        contentColor: Color = MaterialTheme.colors.textBlack,
        disabledBackgroundColor: Color = MaterialTheme.colors.backgroundLightGrey,
        disabledContentColor: Color = MaterialTheme.colors.textGrey
    ): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )
}