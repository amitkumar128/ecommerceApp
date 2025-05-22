package com.ecomm.data.repository

import com.ecomm.domain.model.CartModel
import com.ecomm.domain.model.request.AddCartRequestModel
import com.ecomm.domain.network.NetworkService
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.repository.CartRepository

class CartRepositoryImpl(val networkService: NetworkService) : CartRepository {

    override suspend fun addProductToCart(addCartRequestModel: AddCartRequestModel, userId: Long): ResultWrapper<CartModel> {
        return networkService.addProductToCart(addCartRequestModel,userId)
    }
}