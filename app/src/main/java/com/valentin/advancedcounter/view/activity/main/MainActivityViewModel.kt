package com.valentin.advancedcounter.view.activity.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.service.navService.MainActivityNavService

class MainActivityViewModel(
    private val navService: MainActivityNavService
) : ViewModel() {

    fun initNavigationForMainActivity(mainActivity: MainActivity) {
        navService.doNavigationServiceSetup(mainActivity)
    }

    fun navigateToHomeFragment() {
        Log.d("DEBUG_TAG", "navigate to HomeFragment")
        navigateTo(R.id.homeFragment)
    }

    fun navigateToSecondFirstFragment() {
        Log.d("DEBUG_TAG", "navigate to SecondFirstFragment")
        navigateTo(R.id.secondFirstFragment)
    }

    fun goBack() {
        navService.getNavController().navigateUp()
    }

    private fun navigateTo(resId: Int) {
        navService.navigateTo(resId)
    }
}