package com.valentin.advancedcounter.model.repository

import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter

interface DataService {

    fun getCounters() : MutableLiveData<MutableList<Counter>>

    fun saveCountersAndPostValue(data: MutableList<Counter>)
}