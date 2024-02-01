package kanti.testonlineshop.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.ui.components.DiscountTag
import kanti.testonlineshop.ui.components.PagingIndicator
import kanti.testonlineshop.ui.components.PastPriceText
import kanti.testonlineshop.ui.components.RatingTag
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.components.buttons.PlusButton
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.backgroundWhite
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.elementPink
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title2
import kanti.testonlineshop.ui.theme.title3

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    images: List<Painter>,
    onFavouriteClick: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) = Surface(
    border = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colors.backgroundLightGrey
    ),
    color = MaterialTheme.colors.backgroundWhite,
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    onClick = onClick
) {
    val currency = product.price.unit
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val pagerState = rememberPagerState { images.size }
            HorizontalPager(
                modifier = Modifier.height(144.dp),
                state = pagerState
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = images[it],
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            PagingIndicator(
                modifier = Modifier.align(Alignment.BottomCenter),
                count = images.size,
                select = pagerState.currentPage
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 6.dp,
                        end = 6.dp
                    ),
                iconId = if (product.favourite) R.drawable.heart_full
                else R.drawable.heart_empty,
                tint = MaterialTheme.colors.elementPink
            ) { onFavouriteClick(!product.favourite) }
        }

        // PAST PRICE
        Spacer(modifier = Modifier.height(2.dp))
        PastPriceText(
            modifier = Modifier.padding(start = 6.dp),
            text = "${product.price.price} $currency",
            color = MaterialTheme.colors.textGrey
        )

        // PRICE AND DISCOUNT
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.padding(start = 6.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .weight(1f, false),
                text = "${product.price.priceWithDiscount} $currency",
                style = MaterialTheme.typography.title2,
                color = MaterialTheme.colors.textBlack,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            DiscountTag(
                text = product.price.discount.toString()
            )
        }

        // TITLE
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp),
            text = product.title,
            style = MaterialTheme.typography.title3,
            color = MaterialTheme.colors.textBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // TEXT
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .padding(start = 6.dp, end = 8.dp)
                .fillMaxWidth()
                .heightIn(37.dp, 37.dp),
            text = product.subtitle,
            style = MaterialTheme.typography.caption1,
            overflow = TextOverflow.Ellipsis
        )

        // RATING
        Spacer(modifier = Modifier.heightIn(4.dp))
        RatingTag(
            modifier = Modifier
                .padding(start = 4.dp),
            feedback = product.feedback,
            normalColor = MaterialTheme.colors.textGrey
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            PlusButton()
        }
    }
}

@Preview
@Composable
fun PreviewProductCard() {
    ProductCard(
        product = Product(),
        images = listOf(
            painterResource(id = R.drawable.d1),
            painterResource(id = R.drawable.d5),
            painterResource(id = R.drawable.d3),
            painterResource(id = R.drawable.d2)
        )
    )
}