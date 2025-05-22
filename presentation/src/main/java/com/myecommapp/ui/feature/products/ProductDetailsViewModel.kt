package com.myecommapp.ui.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecomm.domain.model.request.AddCartRequestModel
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.usecase.AddProductToCartUseCase
import com.myecommapp.model.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(val useCase: AddProductToCartUseCase) : ViewModel() {
    private val _productDetailsState =
        MutableStateFlow<ProductDetailsEvent>(ProductDetailsEvent.Nothing)
    val productDetailsState = _productDetailsState.asStateFlow()

    fun addProductToCart(product: UiProductModel) {
        viewModelScope.launch {
            _productDetailsState.value = ProductDetailsEvent.Loading
            val result = useCase.execute(
                AddCartRequestModel(
                    productId = product.id, productName = product.title, price = product.price, quantity = 1, userId = 1

                ),
                1
            )
            when (result) {
                is ResultWrapper.Success -> {
                    _productDetailsState.value = ProductDetailsEvent.Success(result.value.msg)
                }

                is ResultWrapper.Failure -> {
                    _productDetailsState.value = ProductDetailsEvent.Error(
                        result.exception.message ?: "Something went wrong"
                    )
                }

            }
        }

    }

}

sealed class ProductDetailsEvent {
    data object Loading : ProductDetailsEvent()
    data object Nothing : ProductDetailsEvent()
    data class Success(val message: String) : ProductDetailsEvent()
    data class Error(val message: String) : ProductDetailsEvent()
}