package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second_second.*

class SecondSecondFragment : Fragment(R.layout.fragment_second_second){

    private val viewModel by viewModels<HomeFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counterRvLinear.layoutManager = LinearLayoutManager(activity)
        observeData()
    }

    private fun observeData() {


        viewModel.getCounters().observe(viewLifecycleOwner) { counters ->

            if(counterRvLinear.adapter == null) {

                //init adapter
                Log.d("DEBUG_TAG", "counterRvLinear Adapter not set, setting rn")
                counterRvLinear.adapter = getAdapter(counters)

            } else {

                //notify adapter about changes
                counterRvLinear.adapter?.notifyDataSetChanged()
            }

        }


    }


    private fun getAdapter(counters: MutableList<Counter>) : GenericAdapter<Counter> {

        return GenericAdapter.Builder<Counter>()
            .setData(counters)
            .setLayoutResId(R.layout.counter_item)
            .setViewHolder(getViewHolder())
            .build()
    }



    private fun getViewHolder(): CounterVH {

        return CounterVH.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToDetailsFragment)
            .build()
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
    }

    private fun navigateToDetailsFragment(item: Counter) : Boolean {
        Log.d("DEBUG_TAG", "Going to details fragment")

        //bundle
        val bundle = Bundle()
        bundle.putString("clicks_count", item.numberOfClicks.toString())

        //nav argument
//        val navArgument1= NavArgument.Builder().setDefaultValue("1").build()
//        view?.findNavController()?.graph?.addArgument("key_1", navArgument1)


        view?.findNavController()?.navigate(R.id.detailsFragment, bundle)

        return true

    }
}