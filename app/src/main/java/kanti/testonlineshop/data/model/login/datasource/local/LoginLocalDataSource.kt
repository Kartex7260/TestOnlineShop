package kanti.testonlineshop.data.model.login.datasource.local

import kanti.testonlineshop.data.model.login.User
import kotlinx.coroutines.flow.Flow

interface LoginLocalDataSource {

    val user: Flow<User?>

    suspend fun isRegistered(): Boolean

    suspend fun login(name: String, lastName: String, phone: String): Boolean

    suspend fun register(name: String, lastName: String, phone: String)

    suspend fun logout()
}