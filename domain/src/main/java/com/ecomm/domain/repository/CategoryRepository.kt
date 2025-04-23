package com.ecomm.domain.repository

import com.ecomm.domain.model.CategoryListModel
import com.ecomm.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories(): ResultWrapper<CategoryListModel>
}