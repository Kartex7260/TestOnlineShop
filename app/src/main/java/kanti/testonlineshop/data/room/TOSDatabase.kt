package kanti.testonlineshop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kanti.testonlineshop.data.room.login.LoginDao
import kanti.testonlineshop.data.room.login.LoginEntity
import kanti.testonlineshop.data.room.product.ProductDao
import kanti.testonlineshop.data.room.product.ProductEntity

@Database(
    entities = [
        LoginEntity::class,
        ProductEntity::class
    ],
    version = 1
)
abstract class TOSDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    abstract fun productDao(): ProductDao
}