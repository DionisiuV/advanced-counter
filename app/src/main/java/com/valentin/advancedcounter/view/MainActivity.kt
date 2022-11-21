package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.repository.navService.NavServiceProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {

    private val navService: NavServiceProvider by inject(NavServiceProvider::class.java)
//    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()
//        getNavController()
        setBottomMenu()
    }


    private fun setNavGraph() {
        navService.setNavMainActivityNavGraph(this)
    }

    private fun setBottomMenu() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            setNavRoutes(menuItem)
            true
        }
    }

    //this dont work for some reason ???
//    private fun getNavController() {
//        navController = navService.getNavControllerByActivity(this)
//    }

    private fun setNavRoutes(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.home -> navigateToHomeFragment()
            R.id.home2 -> navigateToSecondFirstFragment()
            else -> throw Exception("no")
        }
    }

    private fun navigateToHomeFragment() {
        Log.d("DEBUG_TAG", "navigate to home fragment")
        navigateTo(R.id.homeFragment)
    }

    private fun navigateToSecondFirstFragment() {
        Log.d("DEBUG_TAG", "navigate to secondFirstFragment")
        navigateTo(R.id.secondFirstFragment)
    }

    private fun navigateTo(resId: Int) {
        navService.getNavControllerByActivity(this).navigate(resId)
    }

}