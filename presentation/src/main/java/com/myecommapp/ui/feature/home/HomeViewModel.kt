package com.myecommapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecomm.domain.model.Product
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.usecase.GetCategoriesUseCase
import com.ecomm.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val categoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts(1)
            val popularProducts = getProducts(2)
            val categories = getCategory()
            if (featured.isEmpty() && popularProducts.isEmpty() && categories.isNotEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured, popularProducts, categories)
        }
    }

    private suspend fun getCategory(): List<String> {
        categoriesUseCase.execute().let { result ->

            when (result) {

                is ResultWrapper.Success -> {
                    return (result).value.categories.map { it.title }
                }

                is ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }

    private suspend fun getProducts(category: Int?): List<Product> {
        getProductUseCase.execute(category).let { result ->
            return when (result) {

                is ResultWrapper.Success -> {
                    (result).value.products
                }

                is ResultWrapper.Failure -> {
                    emptyList()
                }
            }

        }

    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<Product>,
        val popularProduct: List<Product>,
        val categories: List<String>
    ) : HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
