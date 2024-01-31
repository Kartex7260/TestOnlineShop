package kanti.testonlineshop.data.model.login

import kanti.testonlineshop.data.model.login.datasource.local.LoginLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localDataSource: LoginLocalDataSource
) : LoginRepository {

    private val _loginResult = MutableSharedFlow<LoginResult>()
    override val loginResult: Flow<LoginResult> = _loginResult.asSharedFlow()

    override suspend fun login(name: String, lastName: String, phone: String) {
        if (localDataSource.isRegistered()) {
            val res = localDataSource.login(name, lastName, phone)
            _loginResult.emit(if (res) LoginResult.Success else LoginResult.InvalidCredentials)
        } else {
            localDataSource.register(name, lastName, phone)
            _loginResult.emit(LoginResult.Register)
        }
    }
}