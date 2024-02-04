package kanti.testonlineshop.data.model.product

import kanti.testonlineshop.data.model.product.datasource.local.ProductLocalDataSource
import kanti.testonlineshop.data.model.product.datasource.remote.ProductRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {

    override val products: Flow<List<Product>> = localDataSource.products
        .flowOn(Dispatchers.IO)

    override val favouriteProducts: Flow<List<Product>> = localDataSource.favouriteProducts
        .flowOn(Dispatchers.IO)

    override suspend fun getProduct(productId: String): Product? {
        return localDataSource.getProduct(productId)
    }

    override suspend fun loadFromRemote() {
        withContext(Dispatchers.IO) {
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