package com.ecomm.data.network

import com.ecomm.data.model.request.AddToCartRequest
import com.ecomm.data.model.response.CartResponse
import com.ecomm.data.model.response.CategoriesListResponse
import com.ecomm.data.model.response.ProductListResponse
import com.ecomm.domain.model.CartItemModel
import com.ecomm.domain.model.CartModel
import com.ecomm.domain.model.CategoryListModel
import com.ecomm.domain.model.ProductListModel
import com.ecomm.domain.model.request.AddCartRequestModel
import com.ecomm.domain.network.NetworkService
import com.ecomm.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class NetworkServiceImpl(val client: HttpClient) : NetworkService {
    //private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/v2"
    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/v2"

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        val url =
            if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"

        val parameters = if (category != null) {
            mapOf("category" to category)
        } else {
            emptyMap()
        }


        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { dataModels: ProductListResponse ->
                dataModels.toProductList()
            })
    }

    override suspend fun getCategories(): ResultWrapper<CategoryListModel> {
        val url = "$baseUrl/categories"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { categories: CategoriesListResponse ->
                categories.categoriesList()
            }
        )
    }

    override suspend fun addProductToCart(addCartRequestModel: AddCartRequestModel,userId: Long): ResultWrapper<CartModel> {
        val url = "$baseUrl/cart/${userId}"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Post,
            body = AddToCartRequest.fromCartRequestModel(addCartRequestModel),
            mapper = { cartItem: CartResponse ->
                cartItem.toCartModel()
            })
    }

    private suspend inline fun <reified T, R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null
    ): ResultWrapper<R> {
        return try {
            val response = client.request(url) {
                this.method = method
                // Apply query parameters
                url {
                    parameters.forEach { (key, value) ->
                        this.parameters.append(key, value)
                    }
                }
                // Apply headers
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                // Set body for POST, PUT, etc.
                body?.let {
                    setBody(it)
                }

                // Set content type
                contentType(ContentType.Application.Json)
            }.body<T>()
            val result: R = mapper?.invoke(response) ?: response as R
            ResultWrapper.Success(result)
        } catch (e: ClientRequestException) {
            ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
    }


}

