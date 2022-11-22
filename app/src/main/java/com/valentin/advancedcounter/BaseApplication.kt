package com.valentin.advancedcounter

import android.app.Application
import com.valentin.advancedcounter.di.repositoryModule
import com.valentin.advancedcounter.di.viewModelModule
import org.koin.core.context.startKoin

//this should be in a package
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

//GENERAL ISSUES:
// 1. onBackPressed not handled properly :
//    as in requirement * Native BACK button takes users back to the screen they were previously on: if FragmentOne, then FragmentOne, if FragmentTwoOne, then FragmentTwoOne
// 2. FragmentOne LongClick wrong action:
//    as in requirement *  Long clicking an element opens FragmentDetails
// 3.