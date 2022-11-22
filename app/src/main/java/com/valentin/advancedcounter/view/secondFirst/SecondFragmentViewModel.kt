package com.valentin.advancedcounter.view.secondFirst

import androidx.lifecycle.ViewModel
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.service.navService.MainActivityNavService

class SecondFragmentViewModel(
    private val navService: MainActivityNavService
) : ViewModel() {

    fun navigateToSecondSecondFragment() {
        navService.navigateTo(R.id.action_second_first_to_second_second)
    }
}