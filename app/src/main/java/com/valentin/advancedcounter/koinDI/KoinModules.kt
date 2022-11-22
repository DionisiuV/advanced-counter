package com.valentin.advancedcounter.koinDI

import com.valentin.advancedcounter.model.repository.dataService.DataService
import com.valentin.advancedcounter.model.repository.dataService.DataServiceProvider
import com.valentin.advancedcounter.model.repository.navService.MainActivityNavService
import com.valentin.advancedcounter.model.repository.navService.MainActivityNavServiceProvider
import com.valentin.advancedcounter.view.activity.main.MainActivityViewModel
import com.valentin.advancedcounter.view.details.DetailsFragmentViewModel
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import com.valentin.advancedcounter.view.secondFirst.SecondFragmentViewModel
import com.valentin.advancedcounter.view.secondSecond.SecondSecondFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single<DataService> { DataServiceProvider() }

    single<MainActivityNavService> { MainActivityNavServiceProvider() }

}

val viewModelModule = module {

    viewModel { HomeFragmentViewModel(get(), get()) }

    viewModel { SecondFragmentViewModel(get()) }

    viewModel { SecondSecondFragmentViewModel(get(), get()) }

    viewModel { DetailsFragmentViewModel(get()) }

    viewModel { MainActivityViewModel(get()) }

}



