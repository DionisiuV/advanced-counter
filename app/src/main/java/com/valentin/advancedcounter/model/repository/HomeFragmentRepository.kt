package com.valentin.advancedcounter.model.repository

import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter

class HomeFragmentRepository {

    fun getCounters() : MutableLiveData<MutableList<Counter>> {
        val data =  MutableLiveData<MutableList<Counter>>()

        return data
    }
}