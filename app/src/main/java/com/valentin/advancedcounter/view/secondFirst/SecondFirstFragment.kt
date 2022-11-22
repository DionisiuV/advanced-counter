package com.valentin.advancedcounter.view.secondFirst

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.fragment_second_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFirstFragment : Fragment(R.layout.fragment_second_first) {

    private val viewModel: SecondFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigateButton()
    }

    private fun navigateToSecondSecondFragment() {
        viewModel.navigateToSecondSecondFragment()
    }

    private fun setNavigateButton() {
        navigateToSecondSecondFragment.setOnClickListener { navigateToSecondSecondFragment() }
    }
}