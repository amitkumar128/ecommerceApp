package com.ecomm.data.repository

import com.ecomm.domain.model.ProductListModel
import com.ecomm.domain.network.NetworkService
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService) : ProductRepository {
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        return networkService.getProducts(category)
    }

}