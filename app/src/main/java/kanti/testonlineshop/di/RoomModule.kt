package kanti.testonlineshop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kanti.testonlineshop.data.room.TOSDatabase
import kanti.testonlineshop.data.room.login.LoginDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideTOSDatabase(@ApplicationContext context: Context): TOSDatabase = Room
        .databaseBuilder(context, TOSDatabase::class.java, "tos")
        .build()

    @Provides
    @Singleton
    fun provideLoginDao(database: TOSDatabase): LoginDao {
        return database.loginDao()
    }
}