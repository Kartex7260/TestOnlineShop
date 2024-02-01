package kanti.testonlineshop.ui.screens.main.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.buttons.IconTextButton
import kanti.testonlineshop.ui.components.buttons.TagButton
import kanti.testonlineshop.ui.components.card.ProductCard
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.CatalogViewModel
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.CatalogViewModelImpl
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.TagUiState
import kanti.testonlineshop.ui.theme.elementPink
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.title1

@Composable
private fun TopAppBar(
    expandMenu: Boolean = false,
    currentSort: String = "",
    changeExpandMenu: (Boolean) -> Unit = {}
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
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
            contentPadding = PaddingValues(15.dp),
            text = currentSort
        ) {
            changeExpandMenu(!expandMenu)
        }

        IconTextButton(
            preIcon = R.drawable.filter,
            contentPadding = PaddingValues(15.dp),
            text = stringResource(id = R.string.catalog_topbar_filters)
        )
    }
}

@Composable
private fun Tags(
    items: List<TagUiState> = listOf(),
    onTagClick: (String) -> Unit = {},
    onClear: () -> Unit = {}
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
                select = tag.isSelect,
                text = tag.title,
                onClick = { onTagClick(tag.productTag) },
                iconClick = { onClear() }
            )
        }
    }
}

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    toProductScreen: (String) -> Unit = {},
    viewModel: CatalogViewModel = hiltViewModel<CatalogViewModelImpl>()
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentSort by viewModel.sort.collectAsState()
        val currentTag by viewModel.selectedTag.collectAsState()
        Box {
            val expandMenuState = rememberSaveable { mutableStateOf(false) }
            TopAppBar(
                expandMenu = expandMenuState.value,
                currentSort = currentSort.getStringRes(LocalContext.current),
                changeExpandMenu = { newExpandMenuState ->
                    expandMenuState.value = newExpandMenuState
                }
            )

            SortMenu(
                expandMenuState = expandMenuState,
                setSort = { viewModel.setSort(it) }
            )
        }

        val tags by viewModel.tags.collectAsState()
        Tags(
            items = tags,
            onTagClick = { tag -> viewModel.setTag(tag) },
            onClear = { viewModel.clearTag() }
        )

        val products by viewModel.products.collectAsState()
        val process by viewModel.process.collectAsState()
        if (process) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.elementPink
                )
            }
        } else {
            val lazyGridState = rememberSaveable(
                inputs = arrayOf(currentSort, currentTag),
                saver = LazyGridState.Saver
            ) { LazyGridState(0, 0) }
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                state = lazyGridState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(items = products) { product ->
                    key(product.id) {
                        val images by viewModel.getImages(product.id)
                            .collectAsState(initial = listOf())
                        ProductCard(
                            product = product,
                            images = images,
                            onFavouriteClick = {
                                viewModel.setFavourite(product.id, it)
                            }
                        ) {
                            toProductScreen(product.id)
                        }
                    }
                }
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