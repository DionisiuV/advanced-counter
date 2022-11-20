package com.valentin.advancedcounter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()
        setBottomMenu()
    }


    private fun setNavGraph() {

        //init nav host
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        //configure nav graph
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        //setting start destination
        navGraph.setStartDestination(R.id.homeFragment)

        //set nav graph
        navController.graph = navGraph
    }


    private fun setBottomMenu() {
        bottomMenu = bottomNavigationView as BottomNavigationView

        bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    Log.d("DEBUG_TAG", "navigate to home tab")
                    navigateTo(R.id.homeFragment)
                    true
                }
                R.id.home2 -> {
                    Log.d("DEBUG_TAG", "navigate to home2 tab")
                    navigateTo(R.id.secondFirstFragment)
                    true
                }
                else -> throw Exception("no")
            }
        }
    }

    private fun navigateTo(resId: Int) {
        navHostFragment.findNavController().navigate(resId)
    }



}