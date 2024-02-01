package kanti.testonlineshop.data.model.product

import kanti.testonlineshop.data.model.product.datasource.remote.ProductRemoteDataSource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {
}