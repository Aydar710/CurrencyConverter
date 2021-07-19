package com.currencyconverter

import android.app.Application
import com.currencyconverter.data.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(*dataModules)
        }
    }
}
