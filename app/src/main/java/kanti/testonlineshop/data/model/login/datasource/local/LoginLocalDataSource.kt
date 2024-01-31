package kanti.testonlineshop.data.model.login.datasource.local

interface LoginLocalDataSource {

    suspend fun isRegistered(): Boolean

    suspend fun login(name: String, lastName: String, phone: String): Boolean

    suspend fun register(name: String, lastName: String, phone: String)
}