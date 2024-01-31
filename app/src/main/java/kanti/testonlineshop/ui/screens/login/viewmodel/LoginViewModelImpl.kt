package kanti.testonlineshop.ui.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor() : ViewModel(), LoginViewModel {

    private val rusSymbols = "ёйцукенгшщзхъфывапролджэячсмитьбю"

    private val _name = MutableStateFlow(EditTextUiState())
    override val name: StateFlow<EditTextUiState> = _name.asStateFlow()

    private val _lastName = MutableStateFlow(EditTextUiState())
    override val lastName: StateFlow<EditTextUiState> = _lastName.asStateFlow()

    private val _phone = MutableStateFlow(EditTextUiState(valid = false))
    override val phone: StateFlow<EditTextUiState> = _phone.asStateFlow()

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
}