package com.valentin.advancedcounter.view.activity.main

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
        navigateTo(R.id.homeFragment)
    }

    fun navigateToSecondFirstFragment() {
        navigateTo(R.id.secondFirstFragment)
    }

    fun goBack() {
        navService.getNavController().navigateUp()
    }

    private fun navigateTo(resId: Int) {
        navService.navigateTo(resId)
    }
}