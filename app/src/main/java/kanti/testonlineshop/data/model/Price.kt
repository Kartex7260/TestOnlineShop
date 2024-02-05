package kanti.testonlineshop.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Price(
    val price: String = "100",
    val discount: Int = 50,
    val priceWithDiscount: String = "50",
    val unit: String = "₽"
)
