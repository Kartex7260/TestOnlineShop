package kanti.testonlineshop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kanti.testonlineshop.data.room.login.LoginDao
import kanti.testonlineshop.data.room.login.LoginEntity

@Database(
    entities = [
        LoginEntity::class
    ],
    version = 1
)
abstract class TOSDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao
}