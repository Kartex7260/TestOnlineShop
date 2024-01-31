package kanti.testonlineshop.ui.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kanti.testonlineshop.data.model.login.LoginRepository
import kanti.testonlineshop.data.model.login.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel(), LoginViewModel {

    private val rusSymbols = "ёйцукенгшщзхъфывапролджэячсмитьбю"

    private val _name = MutableStateFlow(EditTextUiState())
    override val name: StateFlow<EditTextUiState> = _name.asStateFlow()

    private val _lastName = MutableStateFlow(EditTextUiState())
    override val lastName: StateFlow<EditTextUiState> = _lastName.asStateFlow()

    private val _phone = MutableStateFlow(EditTextUiState(valid = false))
    override val phone: StateFlow<EditTextUiState> = _phone.asStateFlow()

    private val _loginButtonEnabled = MutableStateFlow(true)
    override val loginButtonEnabled: StateFlow<Boolean> = _loginButtonEnabled
        .combine(_name) { enable, name -> enable && name.isValid }
        .combine(_lastName) { acc, lastName -> acc && lastName.isValid }
        .combine(_phone) { acc, phone -> acc && phone.valid }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )

    override val loginResult: SharedFlow<LoginResult> = loginRepository.loginResult
        .onEach { _loginButtonEnabled.value = true }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily
        )

    override fun changeName(name: String) {
        viewModelScope.launch {
            name.forEach { char ->
                rusSymbols.contains(char, true).also { valid ->
                    if (!valid) {
                        _name.update { it.copy(valid = false) }
                        return@launch
                    }
                }
            }
            _name.update { it.copy(valid = true) }
        }
        _name.update {
            it.copy(text = name)
        }
    }

    override fun changeLastName(lastName: String) {
        viewModelScope.launch {
            lastName.forEach { char ->
                rusSymbols.contains(char, true).also { valid ->
                    if (!valid) {
                        _lastName.update { it.copy(valid = false) }
                        return@launch
                    }
                }
            }
            _lastName.update { it.copy(valid = true) }
        }
        _lastName.update {
            it.copy(text = lastName)
        }
    }

    override fun changePhone(phone: String) {
        viewModelScope.launch {
            _phone.update { it.copy(valid = phone.length == 10) }
        }
        _phone.update {
            it.copy(text = phone)
        }
    }

    override fun login() {
        _loginButtonEnabled.value = false
        viewModelScope.launch {
            val name = _name.value.text
            val lastName = _lastName.value.text
            val phone = _phone.value.text

            loginRepository.login(name, lastName, phone)
        }
    }
}