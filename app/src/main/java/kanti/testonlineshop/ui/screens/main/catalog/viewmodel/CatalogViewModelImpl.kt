package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    override val selectedTag: StateFlow<TagUiState?> = _currentTag
        .map { currentTag ->
            if (currentTag == null)
                return@map null
            _tags.firstOrNull { tagUiState ->
                tagUiState.productTag == currentTag
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    private val _sort = MutableStateFlow<SortType?>(null)
    override val sort: StateFlow<SortType?> = _sort.asStateFlow()

    override val products: StateFlow<List<Product>> = productRepository.products
        .combine(_sort) { products, sort ->
            if (sort == null)
                return@combine products
            if (sort.ascending)
                products.sortedBy(sort.sortFunc)
            else
                products.sortedByDescending(sort.sortFunc)
        }
        .combine(_currentTag) { products, tag ->
            if (tag == null || tag == defaultTag)
                return@combine products
            products.filter { it.tags.contains(tag) }
        }
        .flowOn(Dispatchers.Default)
        .onEach {
            if (_process.value)
                _process.value = false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )

    private val _process = MutableStateFlow(false)
    override val process: StateFlow<Boolean> = _process.asStateFlow()

    init {
        _process.value = true
        viewModelScope.launch {
            productRepository.loadFromRemote()
        }
    }

    override fun getImages(productId: String): Flow<List<Any>> {
        return flow {
            val images = when (productId) {
                "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(R.drawable.d6, R.drawable.d5)
                "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(R.drawable.d1, R.drawable.d2)
                "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(R.drawable.d5, R.drawable.d6)
                "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.d3, R.drawable.d4)
                "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.d2, R.drawable.d3)
                "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d6, R.drawable.d1)
                "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d4, R.drawable.d3)
                "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d1, R.drawable.d5)
                else -> listOf<Any>()
            }
            emit(images)
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