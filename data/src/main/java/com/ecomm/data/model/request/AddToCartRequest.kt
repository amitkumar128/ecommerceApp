package com.ecomm.data.model.request

import com.ecomm.domain.model.request.AddCartRequestModel
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val productId: Int?,
    val quantity: Int?,
) {
    companion object {
        fun fromCartRequestModel(
            addToCartRequestModel: AddCartRequestModel
        ) = AddToCartRequest(
            productId = addToCartRequestModel.productId,
            quantity = addToCartRequestModel.quantity,

        )
    }

}