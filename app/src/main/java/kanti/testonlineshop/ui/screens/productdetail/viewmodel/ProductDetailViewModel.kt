package kanti.testonlineshop.ui.screens.productdetail.viewmodel

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductDetailViewModel {

    val product: StateFlow<Product> get() = MutableStateFlow(Product())
    val images: StateFlow<List<Any>> get() = MutableStateFlow(listOf())

    fun loadProduct(productId: String) {}

    fun changeFavourite(productId: String, favourite: Boolean) {}

    companion object : ProductDetailViewModel
}