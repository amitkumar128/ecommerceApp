package com.ecomm.domain.usecase

import com.ecomm.domain.model.request.AddCartRequestModel
import com.ecomm.domain.repository.CartRepository

class AddProductToCartUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(requestModel: AddCartRequestModel, userId: Long) =
        cartRepository.addProductToCart(requestModel, userId)
}