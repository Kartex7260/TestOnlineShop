package kanti.testonlineshop.data.model.product

import androidx.compose.runtime.Immutable
import kanti.testonlineshop.data.model.Feedback
import kanti.testonlineshop.data.model.Info
import kanti.testonlineshop.data.model.Price

@Immutable
data class Product(
    val id: String = "id",
    val title: String = "Title",
    val subtitle: String = "Subtitle",

    val price: Price = Price(),
    val feedback: Feedback? = Feedback(),

    val tags: List<String> = listOf("Tag"),
    val available: Int = 100,
    val description: String = "",

    val info: List<Info> = listOf(Info()),

    val ingredients: String = "",

    val favourite: Boolean = false
)
