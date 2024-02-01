package kanti.testonlineshop.data.model.product.datasource.remote

import kanti.testonlineshop.data.model.product.Product

interface ProductRemoteDataSource {

    suspend fun getProducts(): List<Product> { return listOf() }
}