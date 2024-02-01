package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import android.content.Context
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.product.Product

enum class SortType(
    val sortFunc: (Product) -> Float,
    val ascending: Boolean
) {

    Rating(
        sortFunc = { product ->
            product.feedback.rating
        },
        ascending = false
    ),

    PriceReduction(
        sortFunc = { product ->
            product.price.priceWithDiscount.toFloat()
        },
        ascending = false
    ),

    PriceIncrease(
        sortFunc = { product ->
            product.price.priceWithDiscount.toFloat()
        },
        ascending = true
    )
}

fun SortType?.getStringRes(context: Context): String {
    if (this == null)
        return context.getString(R.string.sort)
    return when(this) {
        SortType.Rating -> context.getString(R.string.sort_rating)
        SortType.PriceReduction -> context.getString(R.string.sort_price_reduction)
        SortType.PriceIncrease -> context.getString(R.string.sort_price_increase)
    }
}