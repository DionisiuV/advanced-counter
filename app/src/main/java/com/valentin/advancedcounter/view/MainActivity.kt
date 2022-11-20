package com.valentin.advancedcounter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.valentin.advancedcounter.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()
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


}