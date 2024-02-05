package kanti.testonlineshop.data.model.login

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val name: String = "",
    val lastName: String = "",
    val phone: String = ""
)
