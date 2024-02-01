package kanti.testonlineshop.data.room.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String = "id",
    val title: String = "Title",
    val subtitle: String = "Subtitle",

    val price: String = "",
    val feedback: String = "",

    val tags: String = "",
    val available: Int = 100,

    val info: String = "",

    val ingredients: String = "",

    val favourite: Boolean = false
)
