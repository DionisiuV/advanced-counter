package com.valentin.advancedcounter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.repository.dataService.DataService

class HomeFragmentViewModel(
    private val dataService: DataService
) : ViewModel() {

    fun getCounters(): LiveData<MutableList<Counter>> {
        return dataService.getCounters()
    }

    fun incrementCounter(position: Int) {
        val counters = dataService.getCounters()
        val newArrayValue = counters.value!!

        newArrayValue[position].numberOfClicks++

        dataService.saveCountersAndPostValue(newArrayValue)
//to many empty lines
    }
}