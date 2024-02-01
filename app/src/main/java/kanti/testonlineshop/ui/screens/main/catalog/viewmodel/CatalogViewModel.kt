package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import kanti.testonlineshop.data.model.product.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CatalogViewModel {

    val tags: StateFlow<List<TagUiState>> get() = MutableStateFlow(listOf(
        TagUiState("Test", true),
        TagUiState("Quest")
    ))

    val products: StateFlow<List<Product>> get() = MutableStateFlow(listOf(
        Product(id = "1"),
        Product(id = "2"),
        Product(id = "3"),
        Product(id = "4"),
        Product(id = "5"),
        Product(id = "6"),
        Product(id = "7"),
        Product(id = "8"),
        Product(id = "9"),
        Product(id = "10"),
        Product(id = "11"),
        Product(id = "12"),
        Product(id = "13"),

    ))

    companion object : CatalogViewModel
}