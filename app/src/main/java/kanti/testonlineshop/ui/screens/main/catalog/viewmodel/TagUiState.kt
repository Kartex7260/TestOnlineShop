package kanti.testonlineshop.ui.screens.main.catalog.viewmodel

import androidx.compose.runtime.Immutable

@Immutable
data class TagUiState(
    val title: String = "",
    val isSelect: Boolean = false,
    val productTag: String = ""
)
