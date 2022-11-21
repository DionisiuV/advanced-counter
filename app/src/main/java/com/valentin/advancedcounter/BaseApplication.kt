package com.valentin.advancedcounter

import android.app.Application
import com.valentin.advancedcounter.di.repositoryModule
import com.valentin.advancedcounter.di.viewModelModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(repositoryModule, viewModelModule ))
        }
    }

    private fun initKoinDI() {
        startKoin {
            modules(listOf(repositoryModule, viewModelModule ))
        }
    }
}