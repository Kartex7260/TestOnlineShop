package kanti.testonlineshop.data.model

data class Price(
    val price: String = "100",
    val discount: Int = 50,
    val priceWithDiscount: String = "50",
    val unit: String = "₽"
)
