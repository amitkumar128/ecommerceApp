package com.myecommapp.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}