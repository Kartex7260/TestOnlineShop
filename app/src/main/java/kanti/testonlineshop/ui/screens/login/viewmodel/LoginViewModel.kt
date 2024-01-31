package kanti.testonlineshop.ui.screens.login.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {

    val name: StateFlow<EditTextUiState> get() = MutableStateFlow(EditTextUiState())
    val lastName: StateFlow<EditTextUiState> get() = MutableStateFlow(EditTextUiState())
    val phone: StateFlow<EditTextUiState> get() = MutableStateFlow(EditTextUiState())

    fun changeName(name: String) {}

    fun changeLastName(lastName: String) {}

    fun changePhone(phone: String) {}

    fun login() {}

    companion object : LoginViewModel
}