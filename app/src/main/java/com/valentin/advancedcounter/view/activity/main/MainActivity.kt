package com.valentin.advancedcounter.view.activity.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        setBottomMenu()
    }

    private fun initNavigation() {
        viewModel.initNavigationForMainActivity(this)
    }

    private fun setBottomMenu() {
        bottomNavigationView.setOnItemSelectedListener { navigateToSelectedMenuItem(it) }
    }

    private fun navigateToSelectedMenuItem(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.home -> viewModel.navigateToHomeFragment()
            R.id.home2 -> viewModel.navigateToSecondFirstFragment()
            else -> throw Exception("Wrong route")
        }

        return true
    }
}