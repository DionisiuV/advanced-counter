package com.valentin.advancedcounter.di

import com.valentin.advancedcounter.model.repository.DataService
import com.valentin.advancedcounter.model.repository.DataServiceProvider
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single<DataService> { DataServiceProvider() }

}

val viewModelModule = module {

    viewModel { HomeFragmentViewModel(get()) }

}



