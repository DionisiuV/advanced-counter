package com.valentin.advancedcounter.view.details

import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.service.dataService.DataService

class DetailsFragmentViewModel(
    private val dataService: DataService
) : ViewModel() {

    fun getCounter(): Counter {
        return dataService.getCounterToBeDisplayed()
    }

}