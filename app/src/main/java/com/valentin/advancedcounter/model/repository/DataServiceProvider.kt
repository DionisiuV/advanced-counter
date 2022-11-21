package com.valentin.advancedcounter.model.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.valentin.advancedcounter.model.data.Counter


class DataServiceProvider : DataService {

    private val providedCounters = MutableLiveData<MutableList<Counter>>()

    override fun getCounters(): MutableLiveData<MutableList<Counter>> {
        if (providedCounters.value == null) {
            providedCounters.value = mutableListOf(
                Counter(),
                Counter(),
                Counter(),
                Counter(),
                Counter(),
                Counter(),
            )
        }//why do you need {} here? {} means turning code into a method

        return providedCounters
    }

//    private fun isProvidedCountersListEmpty():Boolean
//
//    private fun getProviderCountersListHardcodedValue():MutableList<Counter>


    override fun saveCountersAndPostValue(data: MutableList<Counter>) {
        providedCounters.postValue(data)
    }


}