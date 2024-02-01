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
    );

    fun getStringRes(context: Context): String {
        return when(this) {
            Rating -> context.getString(R.string.sort_rating)
            PriceReduction -> context.getString(R.string.sort_price_reduction)
            PriceIncrease -> context.getString(R.string.sort_price_increase)
        }
    }
}