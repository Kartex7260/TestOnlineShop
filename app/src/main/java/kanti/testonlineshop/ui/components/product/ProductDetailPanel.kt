package kanti.testonlineshop.ui.components.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.ui.components.CollapsedPanel
import kanti.testonlineshop.ui.components.PricePanel
import kanti.testonlineshop.ui.components.RatingPanel
import kanti.testonlineshop.ui.components.card.AccountCard
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.elementBlack
import kanti.testonlineshop.ui.theme.largeTitle1
import kanti.testonlineshop.ui.theme.text1
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textDarkGrey
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title1

@Composable
fun ProductDetailPanel(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    product: Product,
    images: List<Any> = listOf(),
    onChangeFavourite: (Boolean) -> Unit = {}
) = Column(
    modifier = modifier
        .verticalScroll(scrollState)
) {
    Box {
        LargeProductImages(
            modifier = Modifier
                .height(410.dp),
            images = images
        )
        FavouriteButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .size(24.dp),
            favourite = product.favourite,
            onChangeFavourite = onChangeFavourite
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 21.dp,
                    bottom = 42.dp
                ),
            onClick = {  }
        ) {
            Image(
                painter = painterResource(id = R.drawable.question_png),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        // Main data
        Text(
            text = product.title,
            style = MaterialTheme.typography.title1,
            color = MaterialTheme.colors.textGrey,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.subtitle,
            style = MaterialTheme.typography.largeTitle1,
            color = MaterialTheme.colors.textBlack
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = pluralStringResource(
                id = R.plurals.product_detail_available,
                count = product.available
            ),
            style = MaterialTheme.typography.text1,
            color = MaterialTheme.colors.textGrey
        )
        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = MaterialTheme.colors.backgroundLightGrey
        )
        Spacer(modifier = Modifier.height(10.dp))

        RatingPanel(feedback = product.feedback)
        Spacer(modifier = Modifier.height(16.dp))

        PricePanel(price = product.price)
        Spacer(modifier = Modifier.height(24.dp))

        // Description title
        Text(
            text = stringResource(id = R.string.product_detail_description),
            style = MaterialTheme.typography.title1,
            color = MaterialTheme.colors.textBlack
        )
        Spacer(modifier = Modifier.height(8.dp))

        CollapsedPanel(
            textExpanded = stringResource(id = R.string.product_description_hide),
            textCollapsed = stringResource(id = R.string.product_description_more_detail)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            AccountCard(
                modifier = Modifier.fillMaxWidth(),
                title = product.title,
                tailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        tint = MaterialTheme.colors.elementBlack
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.text1,
                color = MaterialTheme.colors.textDarkGrey
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Specifications title
        Text(
            text = stringResource(id = R.string.product_detail_specifications),
            style = MaterialTheme.typography.title1,
            color = MaterialTheme.colors.textBlack
        )
        Spacer(modifier = Modifier.height(16.dp))

        InfoPanel(items = product.info)
        Spacer(modifier = Modifier.height(34.dp))

        // Composition title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.product_detail_composition),
                style = MaterialTheme.typography.title1,
                color = MaterialTheme.colors.textBlack
            )

            kanti.testonlineshop.ui.components.buttons.IconButton(
                iconId = R.drawable.copy,
                tint = MaterialTheme.colors.textGrey
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Ingredients
        CollapsedIngredients(
            ingredients = product.ingredients,
            textExpanded = stringResource(id = R.string.product_description_hide),
            textCollapsed = stringResource(id = R.string.product_description_more_detail)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(device = "spec:width=1080px,height=4340px,dpi=440")
@Composable
fun PreviewProductDetailPanel() {
    ProductDetailPanel(
        product = Product(),
        images = listOf(
            R.drawable.d6,
            R.drawable.d5,
            R.drawable.d1
        )
    )
}