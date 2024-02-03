package kanti.testonlineshop.data.model.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    val loginResult: Flow<LoginResult>

    val user: Flow<User?>

    suspend fun login(name: String, lastName: String, phone: String)

    suspend fun logout()
}