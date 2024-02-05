package kanti.testonlineshop.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Feedback(
    val count: Int = 50,
    val rating: Float = 4.5f
)