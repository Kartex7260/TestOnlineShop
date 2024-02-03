package kanti.testonlineshop.data.room.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {

    @Query("SELECT * FROM login LIMIT 1")
    fun getLogin(): Flow<LoginEntity?>

    @Query("SELECT COUNT(*) FROM login")
    suspend fun isLogin(): Long

    @Query("SELECT COUNT(*) FROM login WHERE " +
            "name = :name AND last_name = :lastName AND phone = :phone")
    suspend fun login(name: String, lastName: String, phone: String): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: LoginEntity)

    @Query("DELETE FROM login")
    suspend fun logout()
}