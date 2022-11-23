package com.valentin.advancedcounter.application

import com.valentin.advancedcounter.model.service.dataService.DataService
import com.valentin.advancedcounter.model.service.dataService.DataServiceProvider
import com.valentin.advancedcounter.model.service.navService.MainActivityNavService
import com.valentin.advancedcounter.model.service.navService.MainActivityNavServiceProvider
import com.valentin.advancedcounter.model.service.networkService.MockInterceptor
import com.valentin.advancedcounter.model.service.networkService.NetworkService
import com.valentin.advancedcounter.model.service.networkService.NetworkServiceProvider
import com.valentin.advancedcounter.view.activity.main.MainActivityViewModel
import com.valentin.advancedcounter.view.details.DetailsFragmentViewModel
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import com.valentin.advancedcounter.view.secondFirst.SecondFragmentViewModel
import com.valentin.advancedcounter.view.secondSecond.SecondSecondFragmentViewModel
import okhttp3.Interceptor
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

val networkModule = module {

    single<NetworkService> { NetworkServiceProvider() }

}



