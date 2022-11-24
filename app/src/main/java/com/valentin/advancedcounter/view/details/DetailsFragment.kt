package com.valentin.advancedcounter.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClicksAmountToTv()
        setClickActionOnClicksCounter()
    }

    private fun setClicksAmountToTv() {
        clicksCounterEt.text = viewModel.getCounter().numberOfClicks.toString()
    }

    private fun sendClicksAmount(clicks: Int) {
        viewModel.sendClicksAmount(clicks)
    }

    private fun setClickActionOnClicksCounter() {
        clicksCounterEt.setOnClickListener { sendClicksAmount(Integer.parseInt(clicksCounterEt.text.toString())) }
    }
}