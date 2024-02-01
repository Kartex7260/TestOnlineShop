package kanti.testonlineshop.data.model.product.datasource.local

import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.room.product.ProductDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRoomDataSource @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDataSource {

    override val products: Flow<List<Product>>
        get() = productDao.getAllProducts().map {
            products -> products.map { it.toProduct() }
        }

    override suspend fun getProduct(productId: String): Product? {
        return productDao.getProduct(productId)?.toProduct()
    }

    override suspend fun insert(products: List<Product>) {
        productDao.insert(products.map { it.toProductEntity() })
    }

    override suspend fun deleteAll() {
        productDao.deleteAll()
    }

    override suspend fun getFavouriteData(): List<FavouriteData> {
        return productDao.getFavouriteData()
    }

    override suspend fun setFavouriteData(favouriteData: List<FavouriteData>) {
        productDao.setFavouriteData(favouriteData)
    }

    override suspend fun setFavourite(productId: String, favourite: Boolean) {
        productDao.setFavourite(productId, favourite)
    }
}