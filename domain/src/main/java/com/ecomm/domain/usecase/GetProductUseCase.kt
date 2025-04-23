package com.ecomm.domain.usecase

import com.ecomm.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository){
    suspend fun execute(category: Int?) = repository.getProducts(category)
}