package com.valentin.advancedcounter.view.secondSecond

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.service.dataService.DataService
import com.valentin.advancedcounter.model.service.navService.MainActivityNavService

class SecondSecondFragmentViewModel(
    private val dataService: DataService,
    private val navService: MainActivityNavService,
) : ViewModel() {

    fun getCounters(): LiveData<MutableList<Counter>> {
        return dataService.getCounters()
    }

    fun incrementCounterAndNotify(position: Int) {
        dataService.incrementNumberOfClicksForCounterAtPositionAndPostValue(position)
    }

    fun saveSelectedItemAndNavigateToDetailsFragment(selectedItem: Counter) {
        dataService.saveCounterToBeDisplayed(selectedItem)
        navService.navigateTo(R.id.action_second_second_to_details)
    }
}