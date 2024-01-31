package kanti.testonlineshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kanti.testonlineshop.data.model.login.LoginRepository
import kanti.testonlineshop.data.model.login.LoginRepositoryImpl
import kanti.testonlineshop.data.model.login.datasource.local.LoginLocalDataSource
import kanti.testonlineshop.data.model.login.datasource.local.LoginRoomDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginModule {

    @Binds
    @Singleton
    fun bindLoginRepositoryImpl(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindLoginRoomDataSource(dataSource: LoginRoomDataSource): LoginLocalDataSource
}