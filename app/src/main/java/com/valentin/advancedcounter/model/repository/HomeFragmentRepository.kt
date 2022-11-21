package com.valentin.advancedcounter.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.valentin.advancedcounter.model.data.Counter

interface HomeFragmentRepository {

    fun initCounters(context: Context) : MutableList<Counter>
    fun addDataToSharedPref(data: MutableList<Counter>, context: Context)
    fun fetchDataFromSharedPref(context: Context) : MutableList<Counter>?

}