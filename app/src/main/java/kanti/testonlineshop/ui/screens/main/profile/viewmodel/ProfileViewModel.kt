package kanti.testonlineshop.ui.screens.main.profile.viewmodel

import kanti.testonlineshop.data.model.login.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ProfileViewModel {

    val user: StateFlow<User> get() = MutableStateFlow(User())
    val favouritesCount: StateFlow<Int> get() = MutableStateFlow(0)

    val onLogout: SharedFlow<Unit> get() = MutableSharedFlow()

    fun updateData() {}

    fun logout() {}

    companion object : ProfileViewModel
}