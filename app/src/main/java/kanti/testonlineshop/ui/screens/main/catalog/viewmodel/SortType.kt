package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

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