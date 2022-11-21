package com.valentin.advancedcounter.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavGraph()
        setBottomMenu()
    }

    private fun setNavGraph() {
        viewModel.setNavGraph(this)
    }

    private fun setBottomMenu() {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            setNavRoutes(menuItem)
            true
        }
    }

    private fun setNavRoutes(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.home -> viewModel.navigateToHomeFragment(this)
            R.id.home2 -> viewModel.navigateToSecondFirstFragment(this)
            else -> throw Exception("Wrong route")
        }
    }
}