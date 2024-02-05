package kanti.testonlineshop.data.model.product.datasource.remote

import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.retrofit.product.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRetrofitDataSource @Inject constructor(
    private val productService: ProductService
) : ProductRemoteDataSource {

    override suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            productService.getItems().toProductList()
        }
    }
}