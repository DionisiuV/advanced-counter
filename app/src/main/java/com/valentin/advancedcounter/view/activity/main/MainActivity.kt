package com.valentin.advancedcounter.view.activity.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
        setBottomMenu()
        setBackFunctionality()
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

    private fun setBackFunctionality() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.goBack()
            }
        })
    }
}