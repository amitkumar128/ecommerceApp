package com.ecomm.domain.repository

import com.ecomm.domain.model.CartModel
import com.ecomm.domain.model.request.AddCartRequestModel
import com.ecomm.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        addCartRequestModel: AddCartRequestModel, userId: Long
    ): ResultWrapper<CartModel>

}