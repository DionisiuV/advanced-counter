package com.valentin.advancedcounter.model.repository.navService


import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.activity.main.MainActivity

class NavServiceProvider() : NavService {

    override fun setNavMainActivityNavGraph(mainActivity: MainActivity) {
        
        val navHostFragment = mainActivity.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)
        navController.graph = navGraph
    }

    override fun getNavControllerByActivity(activity: Activity): NavController {
        return activity.findNavController(R.id.navHostFragment)
    }
}