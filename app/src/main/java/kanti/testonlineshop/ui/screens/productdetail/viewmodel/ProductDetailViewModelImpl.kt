package kanti.testonlineshop.ui.screens.productdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kanti.testonlineshop.data.ImageLoader
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModelImpl @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel(), ProductDetailViewModel {

    private val _updateProduct = MutableStateFlow(Any())
    private val _product = MutableStateFlow<String?>(null)
    override val product: StateFlow<Product> = _product
        .combine(_updateProduct) { productId, _ -> productId }
        .map { productId ->
            if (productId == null)
                return@map null
            productRepository.getProduct(productId)
        }
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Product()
        )

    override val images: StateFlow<List<Any>> = product
        .map { product -> ImageLoader.imageFromId(product.id) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )

    override fun loadProduct(productId: String) {
        _product.value = productId
    }

    override fun changeFavourite(productId: String, favourite: Boolean) {
        viewModelScope.launch {
            productRepository.setFavourite(productId, favourite)
            _updateProduct.value = Any()
        }
    }
}