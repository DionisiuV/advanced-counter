package com.valentin.advancedcounter.view.home

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.repository.dataService.DataService
import com.valentin.advancedcounter.model.repository.navService.NavService
import com.valentin.advancedcounter.view.activity.MainActivity

class HomeFragmentViewModel(
    private val dataService: DataService,
    private val navService: NavService
) : ViewModel() {

    fun getCounters(): LiveData<MutableList<Counter>> {
        return dataService.getCounters()
    }

    fun incrementCounter(position: Int) {
        val counters = dataService.getCounters()
        val newArrayValue = counters.value!!

        newArrayValue[position].numberOfClicks++
        dataService.saveCountersAndPostValue(newArrayValue)
    }

    fun setNavGraph(activity: MainActivity) {
        navService.setNavMainActivityNavGraph(activity)
    }

    private fun navigateTo(resId: Int, activity: Activity) {
        navService.getNavControllerByActivity(activity).navigate(resId)
    }

    fun navigateToHomeFragment(activity: Activity) {
        Log.d("DEBUG_TAG", "navigate to home fragment")
        navigateTo(R.id.homeFragment, activity)
    }

    fun navigateToSecondFirstFragment(activity: Activity) {
        Log.d("DEBUG_TAG", "navigate to secondFirstFragment")
        navigateTo(R.id.secondFirstFragment, activity)
    }

    fun navigateToSecondSecondFragment(activity: Activity) {
        Log.d("DEBUG_TAG", "navigate to secondSecondFragment")
        navigateTo(R.id.secondSecondFragment, activity)
    }

    fun navigateToDetailsFragment(activity: Activity) {
        Log.d("DEBUG_TAG", "navigate to detailsFragment")
        navigateTo(R.id.detailsFragment, activity)
    }


}