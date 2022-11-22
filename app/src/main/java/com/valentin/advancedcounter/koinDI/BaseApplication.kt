package com.valentin.advancedcounter.koinDI

import android.app.Application
import com.valentin.advancedcounter.koinDI.repositoryModule
import com.valentin.advancedcounter.koinDI.viewModelModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoinDI()
    }

    private fun  initKoinDI() {
        startKoin {
            modules(listOf(repositoryModule, viewModelModule ))
        }
    }
}