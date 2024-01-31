package kanti.testonlineshop.ui.screens.login.viewmodel

data class EditTextUiState(
    val text: String = "",
    val valid: Boolean = true
) {

    val isValid: Boolean get() = text.isNotBlank() && valid
}
