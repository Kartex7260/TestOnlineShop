package kanti.testonlineshop.data.retrofit.product

data class ProductDTO (
    var id: String = "",
    var title: String = "",
    var subtitle: String = "",

    var price: PriceDTO = PriceDTO(),
    var feedback: FeedbackDTO? = FeedbackDTO(),

    var tags: List<String> = listOf(),

    var available: Int = 0,
    var description: String = "",
    var info: List<InfoDTO> = listOf(),

    var ingredients: String = ""
)