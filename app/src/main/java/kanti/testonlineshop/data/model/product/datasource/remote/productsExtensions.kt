package kanti.testonlineshop.data.model.product.datasource.remote

import kanti.testonlineshop.data.model.Feedback
import kanti.testonlineshop.data.model.Info
import kanti.testonlineshop.data.retrofit.product.PriceDTO
import kanti.testonlineshop.data.retrofit.product.ProductDTO
import kanti.testonlineshop.data.model.Price
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.retrofit.product.FeedbackDTO
import kanti.testonlineshop.data.retrofit.product.InfoDTO
import kanti.testonlineshop.data.retrofit.product.ProductsDTO

fun ProductsDTO.toProductList(): List<Product> {
    return items.map { it.toProduct() }
}

private fun ProductDTO.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        subtitle = subtitle,

        price = price.toPrice(),
        feedback = feedback.toFeedback(),

        tags = tags,
        available = available,

        info = info.map { it.toInfo() },
        ingredients = ingredients,
        favourite = false
    )
}

private fun PriceDTO.toPrice(): Price {
    return Price(
        price = price,
        discount = discount,
        priceWithDiscount = priceWithDiscount,
        unit = unit
    )
}

private fun FeedbackDTO.toFeedback(): Feedback {
    return Feedback(
        count = count,
        rating = rating
    )
}

private fun InfoDTO.toInfo(): Info {
    return Info(
        title = title,
        value = value
    )
}