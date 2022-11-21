package com.valentin.advancedcounter.model.repository.navService

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.valentin.advancedcounter.view.MainActivity

interface NavService {

    fun getNavControllerByActivity(activity: Activity) : NavController

    fun setNavMainActivityNavGraph(activity: MainActivity)

//    fun getNavController

//    fun navigateTo(actionId: Int)
}