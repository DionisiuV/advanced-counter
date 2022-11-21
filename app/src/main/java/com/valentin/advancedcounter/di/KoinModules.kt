package com.valentin.advancedcounter.di

import com.valentin.advancedcounter.model.repository.dataService.DataService
import com.valentin.advancedcounter.model.repository.dataService.DataServiceProvider
import com.valentin.advancedcounter.model.repository.navService.NavService
import com.valentin.advancedcounter.model.repository.navService.NavServiceProvider
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single<DataService> { DataServiceProvider() }

    single<NavService> { NavServiceProvider() }

}

val viewModelModule = module {

    viewModel { HomeFragmentViewModel(get(), get()) }

}



