package com.valentin.advancedcounter.model.repository.dataService

import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter


class DataServiceProvider : DataService {

    private val providedCounters = MutableLiveData<MutableList<Counter>>()


    override fun getCounters(): MutableLiveData<MutableList<Counter>> {
        if (isProvidedCountersListEmpty())
            saveCountersAndPostValue(getProviderCountersListHardcodedValue())

        return providedCounters
    }

    private fun isProvidedCountersListEmpty(): Boolean {
        return providedCounters.value == null
    }

    private fun getProviderCountersListHardcodedValue(): MutableList<Counter> {
        return mutableListOf(
            Counter(),
            Counter(),
            Counter(),
            Counter(),
            Counter(),
            Counter(),
        )
    }

    override fun saveCountersAndPostValue(data: MutableList<Counter>) {
        providedCounters.postValue(data)
    }
}