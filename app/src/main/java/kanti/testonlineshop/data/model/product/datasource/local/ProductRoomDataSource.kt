package kanti.testonlineshop.data.model.product.datasource.local

import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.room.product.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRoomDataSource @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDataSource {

    override val products: Flow<List<Product>>
        get() = productDao.getAllProducts().map {
            products -> products.map { it.toProduct() }
        }.flowOn(Dispatchers.IO)

    override val favouriteProducts: Flow<List<Product>>
        get() = productDao.getAllFavouriteProducts().map {
            products -> products.map { it.toProduct() }
        }.flowOn(Dispatchers.IO)

    override suspend fun getProduct(productId: String): Product? {
        return withContext(Dispatchers.IO) {
            productDao.getProduct(productId)?.toProduct()
        }
    }

    override suspend fun insert(products: List<Product>) {
        withContext(Dispatchers.IO) {
            productDao.insert(products.map { it.toProductEntity() })
        }
    }

    override suspend fun deleteAll() {
        productDao.deleteAll()
    }

    override suspend fun getFavouriteData(): List<FavouriteData> {
        return productDao.getFavouriteData()
    }

    override suspend fun getFavouriteCount(): Int {
        return productDao.getFavouriteCount()
    }

    override suspend fun setFavouriteData(favouriteData: List<FavouriteData>) {
        productDao.setFavouriteData(favouriteData)
    }

    override suspend fun setFavourite(productId: String, favourite: Boolean) {
        productDao.setFavourite(productId, favourite)
    }
}