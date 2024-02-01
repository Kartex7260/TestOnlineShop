package kanti.testonlineshop.data.model.product.datasource.remote

import kanti.testonlineshop.data.retrofit.product.ProductService
import javax.inject.Inject

class ProductRetrofitDataSource @Inject constructor(
    private val productService: ProductService
) : ProductRemoteDataSource {
}