package com.ecomm.data.di

import com.ecomm.data.repository.CartRepositoryImpl
import com.ecomm.data.repository.CategoryRepositoryImpl
import com.ecomm.data.repository.ProductRepositoryImpl
import com.ecomm.domain.repository.CartRepository
import com.ecomm.domain.repository.CategoryRepository
import com.ecomm.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<ProductRepository>{ ProductRepositoryImpl(get()) }
    single<CategoryRepository>{ CategoryRepositoryImpl(get()) }
    single<CartRepository>{ CartRepositoryImpl(get()) }
}