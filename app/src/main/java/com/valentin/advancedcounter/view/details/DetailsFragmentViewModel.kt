package com.valentin.advancedcounter.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.service.dataService.DataService
import com.valentin.advancedcounter.model.service.networkService.NetworkService

class DetailsFragmentViewModel(
    private val dataService: DataService,
    private val networkService: NetworkService
) : ViewModel() {

    fun getCounter(): Counter {
        return dataService.getCounterToBeDisplayed()
    }

    fun sendClicksAmount(clicksAmount: Int) {
        networkService.sendClicksAmount(clicksAmount)
    }
}