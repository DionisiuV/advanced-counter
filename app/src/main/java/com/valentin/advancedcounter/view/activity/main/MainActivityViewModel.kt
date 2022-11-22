package com.valentin.advancedcounter.view.activity.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.repository.navService.NavService

class MainActivityViewModel(
    private val navService: NavService
) : ViewModel() {
    fun setNavGraph(mainActivity: MainActivity) {
        navService.setNavMainActivityNavGraph(mainActivity)
    }

    fun navigateToHomeFragment(mainActivity: MainActivity) {
        Log.d("DEBUG_TAG", "navigate to home fragment")
        navigateTo(R.id.homeFragment, mainActivity)
    }

    fun navigateToSecondFirstFragment(mainActivity: MainActivity) {
        Log.d("DEBUG_TAG", "navigate to secondFirstFragment")
        navigateTo(R.id.secondFirstFragment, mainActivity)
    }

    private fun navigateTo(resId: Int, mainActivity: MainActivity) {
        navService.getNavControllerByActivity(mainActivity).navigate(resId)
    }
}