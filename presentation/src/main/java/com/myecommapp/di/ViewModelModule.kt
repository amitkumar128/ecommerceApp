package com.myecommapp.di

import com.myecommapp.ui.feature.home.HomeViewModel
import com.myecommapp.ui.feature.products.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel{
        ProductDetailsViewModel()
    }
}