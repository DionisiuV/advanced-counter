package com.valentin.advancedcounter.model.service.navService

import com.valentin.advancedcounter.view.activity.main.MainActivity

interface MainActivityNavService {

    fun doNavigationServiceSetup(activity: MainActivity)

    fun getNavGraph(): Int

    fun navigateTo(destinationOrAction: Int)
}