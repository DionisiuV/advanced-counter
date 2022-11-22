package com.valentin.advancedcounter.model.service.dataService

import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter

interface DataService {

    fun getCounters(): MutableLiveData<MutableList<Counter>>

    fun incrementNumberOfClicksForCounterAtPositionAndPostValue(position: Int)

    fun saveCounterToBeDisplayed(counter: Counter)

    fun getCounterToBeDisplayed(): Counter

    fun clearCounterToBeDisplayed()

}