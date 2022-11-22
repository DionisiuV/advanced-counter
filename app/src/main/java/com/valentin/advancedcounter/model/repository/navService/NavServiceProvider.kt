package com.valentin.advancedcounter.model.repository.navService


import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.activity.MainActivity

class NavServiceProvider : NavService {

    override fun setNavMainActivityNavGraph(activity: MainActivity) {

        //init nav host              // irrelevant comment
        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        //configure nav graph       // irrelevant comment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        //setting start destination
        navGraph.setStartDestination(R.id.homeFragment)

        //set nav graph             // irrelevant comment
        navController.graph = navGraph
    }

    override fun getNavControllerByActivity(activity: Activity): NavController {
        return activity.findNavController(R.id.navHostFragment)
    }
}