package kanti.testonlineshop.ui.screens.productdetail.viewmodel

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductDetailViewModel {

    val product: StateFlow<Product> get() = MutableStateFlow(Product())

    fun loadProduct(productId: String) {}

    companion object : ProductDetailViewModel
}