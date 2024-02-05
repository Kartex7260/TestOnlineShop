package kanti.testonlineshop.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Info(
    val title: String = "Title",
    val value: String = "Value"
)
