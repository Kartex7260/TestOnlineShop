package kanti.testonlineshop.data.retrofit.product

data class PriceDTO(
    var price: String = "",
    var discount: Int = 0,
    var priceWithDiscount: String = "",
    var unit: String = ""
)