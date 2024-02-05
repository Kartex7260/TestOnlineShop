package kanti.testonlineshop.data.model.product

import kanti.testonlineshop.data.model.product.datasource.local.ProductLocalDataSource
import kanti.testonlineshop.data.model.product.datasource.remote.ProductRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {

    override val products: Flow<List<Product>> = localDataSource.products

    override val favouriteProducts: Flow<List<Product>> = localDataSource.favouriteProducts

    override suspend fun getProduct(productId: String): Product? {
        return localDataSource.getProduct(productId)
    }

    override suspend fun loadFromRemote() {
        withContext(Dispatchers.Default) {
            val products = remoteDataSource.getProducts()
            val favouriteData = localDataSource.getFavouriteData()
            localDataSource.deleteAll()
            localDataSource.insert(products)

            if (favouriteData.isNotEmpty())
                localDataSource.setFavouriteData(favouriteData)
        }
    }

    override suspend fun setFavourite(productId: String, favourite: Boolean) {
        localDataSource.setFavourite(productId, favourite)
    }

    override suspend fun getFavouritesCount(): Int {
        return localDataSource.getFavouriteCount()
    }
}