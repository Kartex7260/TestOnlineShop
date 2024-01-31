package kanti.testonlineshop.data.model.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override val loginResult: Flow<LoginResult>
        get() = MutableSharedFlow()

    override suspend fun login(name: String, lastName: String, phone: String) {
    }
}