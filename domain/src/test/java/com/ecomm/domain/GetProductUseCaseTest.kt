package com.ecomm.domain

import com.ecomm.domain.model.Product
import com.ecomm.domain.model.ProductListModel
import com.ecomm.domain.network.ResultWrapper
import com.ecomm.domain.repository.ProductRepository
import com.ecomm.domain.usecase.GetProductUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetProductsUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var useCase: GetProductUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetProductUseCase(repository)
    }

    @Test
    fun `execute should return success with product list for a given category`() = runTest {
        // Given
        val categoryId: Int? = 1
        val productList = listOf(
            Product(
                id = 101,
                title = "Smartphone",
                price = 699.99,
                description = "Latest model with AMOLED display",
                categoryId = categoryId ?: 0,
                image = "https://example.com/images/smartphone.jpg"
            ),
            Product(
                id = 102,
                title = "Bluetooth Headphones",
                price = 199.99,
                description = "Noise-cancelling over-ear headphones",
                categoryId = categoryId ?: 0,
                image = "https://example.com/images/headphones.jpg"
            )
        )
        val productListModel = ProductListModel(products = productList,"msg")
        val expectedResult = ResultWrapper.Success(productListModel)

        coEvery { repository.getProducts(categoryId) } returns expectedResult

        // When
        val result = useCase.execute(categoryId)

        // Then
        assertEquals(expectedResult, result)
        coVerify { repository.getProducts(categoryId) }
    }
}
