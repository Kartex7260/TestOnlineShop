package kanti.testonlineshop.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.data.model.Price
import kanti.testonlineshop.ui.theme.price
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun PricePanel(
    modifier: Modifier = Modifier,
    price: Price
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        modifier = Modifier.offset(x = 0.dp, y = (-2).dp),
        text = "${price.priceWithDiscount} ${price.unit}",
        style = MaterialTheme.typography.price,
        color = MaterialTheme.colors.textBlack
    )
    Spacer(modifier = Modifier.width(11.dp))
    PastPriceText(
        text = "${price.price} ${price.unit}",
        color = MaterialTheme.colors.textGrey
    )
    Spacer(modifier = Modifier.width(14.dp))
    DiscountTag(
        text = price.discount.toString()
    )
}

@Preview
@Composable
private fun PreviewPricePanel() {
    PricePanel(
        price = Price()
    )
}