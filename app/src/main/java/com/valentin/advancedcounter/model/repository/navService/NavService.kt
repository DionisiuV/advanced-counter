package com.valentin.advancedcounter.model.repository.navService

import android.app.Activity
import androidx.navigation.NavController
import com.valentin.advancedcounter.view.activity.main.MainActivity

interface NavService {

    fun getNavControllerByActivity(activity: Activity) : NavController

    fun setNavMainActivityNavGraph(mainActivity: MainActivity)
}