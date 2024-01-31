package kanti.testonlineshop.data.model.login

sealed class LoginResult {

    data object Register : LoginResult()

    data object Success : LoginResult()

    data object InvalidCredentials : LoginResult()
}