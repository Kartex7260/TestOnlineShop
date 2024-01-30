package kanti.testonlineshop.ui.components.normalbutton

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.components.PastPriceText
import kanti.testonlineshop.ui.theme.backgroundLightPink
import kanti.testonlineshop.ui.theme.backgroundPink
import kanti.testonlineshop.ui.theme.button2
import kanti.testonlineshop.ui.theme.textLightPink
import kanti.testonlineshop.ui.theme.textWhite

@Composable
fun PriceNormalButton(
    modifier: Modifier = Modifier,
    price: String = "000",
    discount: String = "000",
    rightText: String = "Label",
    currency: String = "₽",
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = Button(
    onClick = onClick,
    modifier = modifier
        .height(51.dp),
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.backgroundPink,
        contentColor = MaterialTheme.colors.textWhite,
        disabledBackgroundColor = MaterialTheme.colors.backgroundLightPink,
        disabledContentColor = MaterialTheme.colors.textWhite
    ),
    shape = RoundedCornerShape(8.dp),
    contentPadding = PaddingValues(
        start = 16.dp,
        end = 16.dp
    )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = price + currency,
            style = MaterialTheme.typography.button2
        )
        Spacer(modifier = Modifier.width(9.dp))
        PastPriceText(
            text = discount + currency,
            color = if (enabled) MaterialTheme.colors.textLightPink
            else MaterialTheme.colors.textWhite
        )
    }

    Spacer(modifier = Modifier.width(16.dp))

    Text(
        text = rightText,
        style = MaterialTheme.typography.button2
    )
}

@Preview
@Composable
fun PreviewPriceNormalButton() {
    PriceNormalButton(
    )
}
