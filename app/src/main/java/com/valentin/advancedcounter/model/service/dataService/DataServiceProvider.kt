package com.valentin.advancedcounter.model.service.dataService

import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter


class DataServiceProvider : DataService {

    private val providedCounters = MutableLiveData<MutableList<Counter>>()
    private var counterToBeDisplayed: Counter? = null


    override fun getCounters(): MutableLiveData<MutableList<Counter>> {
        if (isProvidedCountersListEmpty())
            setHardcodedValueOfProvidedCounters()

        return providedCounters
    }

    override fun incrementNumberOfClicksForCounterAtPositionAndPostValue(position: Int) {
        val newArrayValue = getCounters().value!!

        newArrayValue[position].numberOfClicks++

        providedCounters.postValue(newArrayValue)
    }

    override fun saveCounterToBeDisplayed(counter: Counter) {
        this.counterToBeDisplayed = counter
    }

    override fun getCounterToBeDisplayed(): Counter {
        return counterToBeDisplayed!!
    }

    override fun clearCounterToBeDisplayed() {
        counterToBeDisplayed = null
    }

    private fun isProvidedCountersListEmpty(): Boolean {
        return providedCounters.value == null
    }

    private fun setHardcodedValueOfProvidedCounters() {
        providedCounters.value = mutableListOf(
            Counter(),
            Counter(),
            Counter(),
            Counter(),
            Counter(),
            Counter(),
        )
    }

}