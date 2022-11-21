package com.valentin.advancedcounter.di

import com.valentin.advancedcounter.model.repository.HomeFragmentRepository
import com.valentin.advancedcounter.model.repository.MockFragmentRepositoryImpl
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeFragmentRepository> { MockFragmentRepositoryImpl() }
}

val viewModelModule = module {
    viewModel {
        HomeFragmentViewModel()
    }
}



