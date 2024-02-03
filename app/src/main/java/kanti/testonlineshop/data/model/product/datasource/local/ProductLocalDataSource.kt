package kanti.testonlineshop.data.model.product.datasource.local

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface ProductLocalDataSource {

    val products: Flow<List<Product>> get() = MutableSharedFlow()

    val favouriteProducts: Flow<List<Product>> get() = MutableSharedFlow()

    suspend fun getProduct(productId: String): Product? { return null }

    suspend fun insert(products: List<Product>) {}

    suspend fun deleteAll() {}

    suspend fun getFavouriteData(): List<FavouriteData> { return listOf() }

    suspend fun getFavouriteCount(): Int

    suspend fun setFavouriteData(favouriteData: List<FavouriteData>) {}

    suspend fun setFavourite(productId: String, favourite: Boolean) {}
}