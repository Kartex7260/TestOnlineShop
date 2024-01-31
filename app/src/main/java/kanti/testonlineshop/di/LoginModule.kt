package kanti.testonlineshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kanti.testonlineshop.data.model.login.LoginRepository
import kanti.testonlineshop.data.model.login.LoginRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginModule {

    @Binds
    @Singleton
    fun bindLoginRepositoryImpl(repository: LoginRepositoryImpl): LoginRepository
}