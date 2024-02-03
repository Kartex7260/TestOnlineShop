package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CatalogViewModel {

    val tags: StateFlow<List<TagUiState>> get() = MutableStateFlow(listOf())
    val products: StateFlow<List<Product>> get() = MutableStateFlow(listOf())

    val process: StateFlow<Boolean> get() = MutableStateFlow(false)

    val sort: StateFlow<SortType?> get() = MutableStateFlow(SortType.Rating)
    val selectedTag: StateFlow<TagUiState?> get() = MutableStateFlow(TagUiState())

    fun loadProducts() {}

    fun getImages(productId: String): Flow<List<Any>> { return MutableStateFlow(listOf()) }

    fun setTag(productTag: String) {}
    fun clearTag() {}

    fun setSort(sort: SortType) {}

    fun setFavourite(productId: String, favourite: Boolean) {}

    companion object : CatalogViewModel
}