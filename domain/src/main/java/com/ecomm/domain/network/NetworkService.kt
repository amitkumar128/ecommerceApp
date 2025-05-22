package com.ecomm.domain.network

import com.ecomm.domain.model.CartItemModel
import com.ecomm.domain.model.CartModel
import com.ecomm.domain.model.CategoryListModel
import com.ecomm.domain.model.ProductListModel
import com.ecomm.domain.model.request.AddCartRequestModel

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoryListModel>

    suspend fun addProductToCart(addCartRequestModel: AddCartRequestModel, userId:Long): ResultWrapper<CartModel>


}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}