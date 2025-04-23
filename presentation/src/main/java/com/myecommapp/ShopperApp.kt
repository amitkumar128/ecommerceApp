package com.myecommapp

import android.app.Application
import android.util.Log
import com.ecomm.data.di.dataModule
import com.ecomm.domain.di.domainModule
import com.myecommapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            startKoin {
                androidContext(this@ShopperApp)
                modules(
                    listOf(
                        domainModule,
                        dataModule,
                        presentationModule

                    )
                )
            }
        }catch (e: Exception){
            Log.e("Koin", "Failed to start Koin", e)
        }

    }

}