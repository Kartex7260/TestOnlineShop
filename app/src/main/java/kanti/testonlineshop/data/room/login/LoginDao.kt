package kanti.testonlineshop.data.room.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {

    @Query("SELECT COUNT(*) FROM login")
    suspend fun isLogin(): Long

    @Query("SELECT COUNT(*) FROM login WHERE " +
            "name = :name AND last_name = :lastName AND phone = :phone")
    suspend fun login(name: String, lastName: String, phone: String): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: LoginEntity)
}