package com.valentin.advancedcounter.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.repository.HomeFragmentRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeFragmentViewModel : ViewModel(), KoinComponent {

    private val repo: HomeFragmentRepository by inject()
    private var counters = MutableLiveData<MutableList<Counter>>()

    fun getCounters(context: Context): LiveData<MutableList<Counter>> {
        if (isListEmpty())
            counters.postValue(repo.initCounters(context))

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

//    private fun getInitialList(): ArrayList<Counter> {
//
//        return arrayListOf(
//            Counter(),
//            Counter(),
//            Counter(),
//            Counter(),
//            Counter(),
//            Counter(),
//        )
//    }
}