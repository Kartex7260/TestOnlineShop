package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val defaultTag = "defTag"
private fun tags(context: Context): List<TagUiState> = listOf(
    TagUiState(
        title = context.getString(R.string.tag_watch_all),
        productTag = defaultTag
    ),
    TagUiState(
        title = context.getString(R.string.tag_face_name),
        productTag = context.getString(R.string.tag_face_data)
    ),
    TagUiState(
        title = context.getString(R.string.tag_body_name),
        productTag = context.getString(R.string.tag_body_data)
    ),
    TagUiState(
        title = context.getString(R.string.tag_suntan_name),
        productTag = context.getString(R.string.tag_suntan_data)
    ),
    TagUiState(
        title = context.getString(R.string.tag_mask_name),
        productTag = context.getString(R.string.tag_mask_data)
    )
)

@HiltViewModel
class CatalogViewModelImpl @Inject constructor(
    private val productRepository: ProductRepository,
    @ApplicationContext context: Context
) : ViewModel(), CatalogViewModel {

    private val _tags = tags(context)

    private val _sort = MutableStateFlow<SortType?>(null)
    override val products: StateFlow<List<Product>> = productRepository.products
        .combine(_sort) { products, sort ->
            if (sort == null)
                return@combine products
            if (sort.ascending)
                products.sortedBy(sort.sortFunc)
            else
                products.sortedByDescending(sort.sortFunc)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )

    private val _currentTag = MutableStateFlow<String?>(defaultTag)
    override val tags: StateFlow<List<TagUiState>> = _currentTag.map { currentTag ->
        _tags.map { tag ->
            tag.copy(isSelect = tag.productTag == currentTag)
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )

    init {
        viewModelScope.launch {
            productRepository.loadFromRemote()
        }
    }

    override fun setTag(productTag: String) { _currentTag.value = productTag }

    override fun clearTag() { _currentTag.value = null }

    override fun setSort(sort: SortType) { _sort.value = sort }

    override fun setFavourite(productId: String, favourite: Boolean) {
        viewModelScope.launch {
            productRepository.setFavourite(productId = productId, favourite = favourite)
        }
    }
}