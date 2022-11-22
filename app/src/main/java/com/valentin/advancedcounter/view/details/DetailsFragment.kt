package com.valentin.advancedcounter.view.details

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClicksAmountToTv()
        Log.d("DEBUG_TAG", "navigateToDetailsFragment() clicks amount: ${getClicksAmountFromSharedPref().toString()}")
    }

    //when user leaving the fragment clear shared pref
    //what shared prefs? - you don't need them -> used for display clicks amount in fragment details, need to clear after that
    override fun onPause() {
        super.onPause()

        val sharedPref = activity?.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        sharedPref?.edit()?.clear()?.apply()
    }

    private fun setClicksAmountToTv() {
        clicksCounterEt.text = getClicksAmountFromSharedPref()
    }

    private fun getClicksAmountFromSharedPref(): String? {
        val sharedPref = activity?.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        return  sharedPref?.getString("clicks_amount", (0).toString())
    }
}