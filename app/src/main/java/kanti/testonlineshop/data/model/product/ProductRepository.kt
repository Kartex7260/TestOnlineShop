package kanti.testonlineshop.data.model.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface ProductRepository {

    val products: Flow<List<Product>> get() = MutableSharedFlow()

    suspend fun getProduct(productId: String): Product? { return null }

    suspend fun loadFromRemote() {}

    suspend fun setFavourite(productId: String, favourite: Boolean) {}

    suspend fun getFavouritesCount(): Int { return 0 }
}