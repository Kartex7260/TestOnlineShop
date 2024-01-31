package kanti.testonlineshop.data.model.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    val loginResult: Flow<LoginResult>

    suspend fun login(name: String, lastName: String, phone: String)
}