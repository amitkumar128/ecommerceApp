package com.ecomm.domain.model

data class Product(
    val id: Int?,
    val title: String,
    val price: Double?,
    val description: String,
    val categoryId: Int,
    val image: String
) {
    val priceString: String
        get() = "$price"
}
