package kanti.testonlineshop.ui.screens.main.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.ProductCard
import kanti.testonlineshop.ui.components.buttons.IconTextButton
import kanti.testonlineshop.ui.components.buttons.TagButton
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.CatalogViewModel
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.TagUiState
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.title1

@Composable
private fun TopAppBar() {
    Spacer(modifier = Modifier.height(17.dp))
    Text(
        text = stringResource(id = R.string.main_catalog),
        style = MaterialTheme.typography.title1,
        color = MaterialTheme.colors.textBlack
    )

    Spacer(modifier = Modifier.height(7.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 1.dp,
                end = 1.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconTextButton(
            preIcon = R.drawable.sort,
            postIcon = R.drawable.down_arrow,
            contentPadding = PaddingValues(15.dp)
        )

        IconTextButton(
            preIcon = R.drawable.filter,
            contentPadding = PaddingValues(15.dp)
        )
    }
}

@Composable
private fun Tags(
    items: List<TagUiState> = listOf()
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 2.dp,
            bottom = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(
            items = items,
            key = { it.title }
        ) { tag ->
            TagButton(
                tailIcon = R.drawable.tag_clear,
                select = tag.isSelect
            )
        }
    }
}

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    toProductScreen: (String) -> Unit = {},
    viewModel: CatalogViewModel = CatalogViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar()

        val tags by viewModel.tags.collectAsState()
        Tags(items = tags)

        val products by viewModel.products.collectAsState()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(
                items = products,
                key = { it.id }
            ) { product ->
                ProductCard(
                    product = product,
                    images = listOf()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCatalogScreen() {
    CatalogScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = CatalogViewModel
    )
}