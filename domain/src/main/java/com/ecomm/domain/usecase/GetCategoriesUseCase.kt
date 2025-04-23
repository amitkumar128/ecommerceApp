package com.ecomm.domain.usecase

import com.ecomm.domain.repository.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend fun execute() = categoryRepository.getCategories()
}