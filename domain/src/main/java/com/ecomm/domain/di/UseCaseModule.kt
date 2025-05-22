package com.ecomm.domain.di

import com.ecomm.domain.usecase.AddProductToCartUseCase
import com.ecomm.domain.usecase.GetCategoriesUseCase
import com.ecomm.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddProductToCartUseCase(get()) }

}