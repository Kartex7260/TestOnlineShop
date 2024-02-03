package kanti.testonlineshop.data.model.login.datasource.local

import kanti.testonlineshop.data.model.login.User
import kanti.testonlineshop.data.room.login.LoginDao
import kanti.testonlineshop.data.room.login.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRoomDataSource @Inject constructor(
    private val loginDao: LoginDao
) : LoginLocalDataSource {

    override val user: Flow<User?>
        get() = loginDao.getLogin().map {
            if (it == null) return@map null
            User(
                name = it.name,
                lastName = it.lastName,
                phone = it.phone
            )
        }

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