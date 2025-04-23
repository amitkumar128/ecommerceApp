package com.ecomm.data.model.response

import com.ecomm.data.model.DataProductModel
import com.ecomm.domain.model.ProductListModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val `data`: List<DataProductModel>,
    val msg: String
){
    fun toProductList() = ProductListModel(
        products = `data`.map { it.toProduct() },
        msg = msg
    )
}



