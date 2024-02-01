package kanti.testonlineshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kanti.testonlineshop.data.model.product.ProductRepository
import kanti.testonlineshop.data.model.product.ProductRepositoryImpl
import kanti.testonlineshop.data.model.product.datasource.remote.ProductRemoteDataSource
import kanti.testonlineshop.data.model.product.datasource.remote.ProductRetrofitDataSource

@Module
@InstallIn(SingletonComponent::class)
interface ProductModule {

    @Binds
    fun bindProductRepositoryImpl(repository: ProductRepositoryImpl): ProductRepository

    @Binds
    fun bindProductRetrofitDataSource(
        dataSource: ProductRetrofitDataSource
    ): ProductRemoteDataSource
}