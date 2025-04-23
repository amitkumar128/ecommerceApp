package com.myecommapp.navigation

import com.myecommapp.model.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object  HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
data class ProductDetails(val product: UiProductModel)
