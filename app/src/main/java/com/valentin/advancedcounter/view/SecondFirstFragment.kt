package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_second_first.*

class SecondFirstFragment : Fragment(R.layout.fragment_second_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToSecondSecondFragment.setOnClickListener { navigateToSecondSecondFragment(view) }
        Log.d("DEBUG_TAG", arguments?.getString("clicks_count").toString())
    }

    private fun navigateToSecondSecondFragment(view: View) {

        Log.d("DEBUG_TAG", "Going to second second fragment")



        view.findNavController().navigate(R.id.secondSecondFragment)

    }


}