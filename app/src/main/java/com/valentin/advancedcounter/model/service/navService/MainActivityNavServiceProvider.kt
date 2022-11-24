package com.valentin.advancedcounter.model.service.navService


import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.activity.main.MainActivity

class MainActivityNavServiceProvider : MainActivityNavService {

    private var activity: MainActivity? = null


    override fun doNavigationServiceSetup(activity: MainActivity) {
        if (shouldSetupNavigation())
            initNavService(activity)
        else
            throw Exception("Navigation for MainActivity was already set up!. Mind your BL")
    }

    override fun getNavGraph(): Int {
        return R.navigation.nav_graph
    }

    override fun navigateTo(destinationOrAction: Int) {
        getNavController().navigate(destinationOrAction)
    }

    private fun shouldSetupNavigation(): Boolean {
        return this.activity == null
    }

    private fun initNavService(activity: MainActivity) {
        this.activity = activity
        val navController = getNavController()

        navController.graph = navController.navInflater.inflate(getNavGraph())
    }

    private fun getNavHost(): NavHost {
        return getActivity().supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    override fun getNavController(): NavController {
        return getNavHost().navController
    }

    private fun getActivity(): MainActivity {
        return activity!!
    }
}