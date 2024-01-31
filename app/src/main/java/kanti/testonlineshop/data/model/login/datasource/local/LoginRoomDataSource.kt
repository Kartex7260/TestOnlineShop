package kanti.testonlineshop.data.model.login.datasource.local

import kanti.testonlineshop.data.room.login.LoginDao
import kanti.testonlineshop.data.room.login.LoginEntity
import javax.inject.Inject

class LoginRoomDataSource @Inject constructor(
    private val loginDao: LoginDao
) : LoginLocalDataSource {

    override suspend fun isRegistered(): Boolean {
        return loginDao.isLogin() != 0L
    }

    override suspend fun login(name: String, lastName: String, phone: String): Boolean {
        return loginDao.login(name, lastName, phone) != 0L
    }

    override suspend fun register(name: String, lastName: String, phone: String) {
        val loginEntity = LoginEntity(name, lastName, phone)
        loginDao.insert(loginEntity)
    }
}