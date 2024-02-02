package kanti.testonlineshop.ui.screens.productdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kanti.testonlineshop.data.ImageLoader
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModelImpl @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel(), ProductDetailViewModel {

    private val _product = MutableStateFlow(Product())
    override val product: StateFlow<Product> = _product.asStateFlow()

    private val _images = MutableStateFlow(listOf<Any>())
    override val images: StateFlow<List<Any>> = _images.asStateFlow()

    override fun loadProduct(productId: String) {
        viewModelScope.launch {
            val product = productRepository.getProduct(productId) ?: return@launch
            _images.value = ImageLoader.imageFromId(productId)
            _product.value = product
        }
    }
}