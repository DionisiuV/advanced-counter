package com.valentin.advancedcounter.model.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valentin.advancedcounter.model.data.Counter
import org.koin.core.component.KoinComponent


class MockFragmentRepositoryImpl : HomeFragmentRepository, KoinComponent {
    override fun initCounters(context: Context): MutableList<Counter> {
        val dataSet = fetchDataFromSharedPref(context)

        if(dataSet.isNullOrEmpty()) {
            return mutableListOf(
                Counter(),
                Counter(),
                Counter(),
                Counter(),
                Counter(),
                Counter(),
            )
        }

        return dataSet
    }


    override fun addDataToSharedPref(data: MutableList<Counter>, context: Context) {
        val sharedPref = context.getSharedPreferences("shared pref", MODE_PRIVATE)

        sharedPref.edit().putString("counters", Gson().toJson(data)).apply()

        Log.d("DEBUG_TAG", "Counters list saved to shared pref")
    }


    override fun fetchDataFromSharedPref(context: Context): MutableList<Counter>? {
        Log.d("DEBUG_TAG", "Fetching counters list from shared pref")

        val json = context.getSharedPreferences("shared pref", MODE_PRIVATE).getString("counters", null)
        val type = object: TypeToken<MutableList<Counter>>() {}.type


        return Gson().fromJson(json, type) as MutableList<Counter>?
    }


}