package com.valentin.advancedcounter.view.secondFirst

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFirstFragment : Fragment(R.layout.fragment_second_first) {

    private val viewModel : HomeFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigateButton()
    }

    private fun navigateToSecondSecondFragment() {
        viewModel.navigateToSecondSecondFragment(requireActivity())//do not pass context/view as param into ViewModel
    }

    private fun setNavigateButton() {
        navigateToSecondSecondFragment.setOnClickListener { navigateToSecondSecondFragment() }
    }
}