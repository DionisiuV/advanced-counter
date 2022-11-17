package com.valentin.advancedcounter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.model.data.Counter

class HomeFragmentViewModel : ViewModel() {

    private var counters = MutableLiveData<MutableList<Counter>>()


    fun getCounters(): LiveData<MutableList<Counter>> {
        if (isListEmpty())
            counters.postValue(getInitialList())

        return counters
    }

    fun incrementCounter(position: Int) {
        val newArrayValue = counters.value!!

        newArrayValue[position].numberOfClicks++

        counters.postValue(newArrayValue)
    }

    private fun isListEmpty(): Boolean {
        return counters.value == null
    }

    private fun getInitialList(): ArrayList<Counter> {
        return arrayListOf(Counter(0), Counter(0))
    }
}