package kanti.testonlineshop.data.model.product.datasource.local

import kanti.testonlineshop.data.model.Feedback
import kanti.testonlineshop.data.model.Info
import kanti.testonlineshop.data.model.Price
import kanti.testonlineshop.data.model.product.Product
import kanti.testonlineshop.data.room.product.ProductEntity

private const val separator1 = "-"
private const val separator2 = ":"

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        subtitle = subtitle,

        price = price.toPrice(),
        feedback = feedback.toFeedback(),

        tags = tags.toTags(),
        available = available,
        description = description,

        info = info.toInfo(),

        ingredients = ingredients,
        favourite = favourite
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        subtitle = subtitle,

        price = price.toPriceStg(),
        feedback = feedback.toFeedbackStg(),

        tags = tags.toTagStg(),
        available = available,
        description = description,

        info = info.toInfoStg(),

        ingredients = ingredients,
        favourite = favourite
    )
}

private fun String.toPrice(): Price {
    val fragments = split(separator1)
    return Price(
        price = fragments[0],
        discount = fragments[1].toInt(),
        priceWithDiscount = fragments[2],
        unit = fragments[3]
    )
}

private fun Price.toPriceStg(): String {
    return buildString {
        append(price).append(separator1)
        append(discount).append(separator1)
        append(priceWithDiscount).append(separator1)
        append(unit)
    }
}

private fun String?.toFeedback(): Feedback? {
    if (this == null) return null
    val fragments = split(separator1)
    return Feedback(
        count = fragments[0].toInt(),
        rating = fragments[1].toFloat()
    )
}

private fun Feedback?.toFeedbackStg(): String? {
    if (this == null) return null
    return buildString {
        append(count).append(separator1)
        append(rating)
    }
}

private fun String.toTags(): List<String> = split(separator1)

private fun List<String>.toTagStg(): String {
    return buildString {
        this@toTagStg.forEachIndexed { index, tag ->
            append(tag)
            if (index < this@toTagStg.size - 1)
                append(separator1)
        }
    }
}

private fun String.toInfo(): List<Info> {
    val fragments1 = split(separator1)
    return buildList {
        for (fragment in fragments1) {
            val fragments2 = fragment.split(separator2)
            add(Info(
                title = fragments2[0],
                value = fragments2[1]
            ))
        }
    }
}

private fun List<Info>.toInfoStg(): String {
    return buildString {
        this@toInfoStg.forEachIndexed { index, info ->
            append(info.title).append(separator2)
            append(info.value)
            if (index < this@toInfoStg.size - 1)
                append(separator1)
        }
    }
}