package com.ecomm.data.model.response

import com.ecomm.domain.model.CategoryListModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse(
    @SerialName("data") val categories: List<CategoryDataModel>,
    @SerialName("msg") val message: String
) {
    fun categoriesList() = CategoryListModel(
        categories = categories.map { it.toCategory() },
        msg = message
    )

}