package com.valentin.advancedcounter.application

import android.app.Application
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoinDI()
    }

    private fun initKoinDI() {
        startKoin {
            modules(listOf(repositoryModule, viewModelModule, networkModule))
        }
    }
}