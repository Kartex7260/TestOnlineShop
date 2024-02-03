package kanti.testonlineshop.ui.screens.main.profile.favourites.products.viewmodel

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface FavouriteProductsViewModel {

    val favouriteProducts: StateFlow<List<Product>> get() = MutableStateFlow(listOf())

    fun getImages(productId: String): Flow<List<Any>> { return MutableStateFlow(listOf()) }

    companion object : FavouriteProductsViewModel
}