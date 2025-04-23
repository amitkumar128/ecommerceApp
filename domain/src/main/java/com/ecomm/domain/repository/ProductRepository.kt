package com.ecomm.domain.repository

import com.ecomm.domain.model.Product
import com.ecomm.domain.model.ProductListModel
import com.ecomm.domain.network.ResultWrapper

interface ProductRepository {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
}