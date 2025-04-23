package com.ecomm.domain.network

import com.ecomm.domain.model.CategoryListModel
import com.ecomm.domain.model.ProductListModel

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoryListModel>


}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}