package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.counter_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeFragmentViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counterRv.layoutManager = GridLayoutManager(activity, 2)
        observeData()
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
    }

    private fun goToFragmentDetails(item: Counter): Boolean {
        //navigate to fragment details with item info
        Log.d("DEBUG_TAG", "navigate to fragment details with item info: $item")

        return true
    }

    private fun observeData() {


        viewModel.getCounters().observe(viewLifecycleOwner) { counters ->

            if(counterRv.adapter == null) {

                //init adapter
                Log.d("DEBUG_TAG", "counterRv Adapter not set, setting rn")
                counterRv.adapter = getAdapter(counters)

            } else {

                //notify adapter about changes
                counterRv.adapter?.notifyDataSetChanged()
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
            .setOnLongClickEventListener(::navigateToSecondFirstFragment)
            .build()
    }

    private fun navigateToSecondFirstFragment(item: Counter) : Boolean {
        Log.d("DEBUG_TAG", "Going to second first fragment")

        //bundle
        val bundle = Bundle()
        bundle.putString("clicks_count", item.numberOfClicks.toString())

        //nav argument
//        val navArgument1= NavArgument.Builder().setDefaultValue("1").build()
//        view?.findNavController()?.graph?.addArgument("key_1", navArgument1)


        view?.findNavController()?.navigate(R.id.secondFirstFragment, bundle)

        return true
    }
}
