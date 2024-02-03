package kanti.testonlineshop.ui.screens.main.profile.favourites.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kanti.testonlineshop.data.ImageLoader
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteProductsViewModelImpl @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel(), FavouriteProductsViewModel {

    override val favouriteProducts: StateFlow<List<Product>> = productRepository.favouriteProducts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )

    override fun getImages(productId: String): Flow<List<Any>> {
        return flowOf(ImageLoader.imageFromId(productId))
    }

    override fun setFavourite(productId: String, favourite: Boolean) {
        viewModelScope.launch {
            productRepository.setFavourite(productId, favourite)
        }
    }
}