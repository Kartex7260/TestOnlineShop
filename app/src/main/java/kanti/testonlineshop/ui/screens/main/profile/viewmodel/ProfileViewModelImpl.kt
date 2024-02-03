package kanti.testonlineshop.ui.screens.main.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kanti.testonlineshop.data.model.login.LoginRepository
import kanti.testonlineshop.data.model.login.User
import kanti.testonlineshop.data.model.product.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    productRepository: ProductRepository
) : ViewModel(), ProfileViewModel {

    private val _updateData = MutableStateFlow(Any())

    override val user: StateFlow<User> = loginRepository.user
        .combine(_updateData) { user, _ -> user }
        .onEach {  }
        .filterNotNull()
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = User())

    override val favouritesCount: StateFlow<Int> = _updateData
        .map { productRepository.getFavouritesCount() }
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = 0)
}