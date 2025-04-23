package com.ecomm.data.repository

import com.ecomm.domain.model.CategoryListModel
import com.ecomm.domain.network.NetworkService
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.repository.CategoryRepository

class CategoryRepositoryImpl(val networkService: NetworkService) : CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<CategoryListModel> {
        return networkService.getCategories()
    }


}