package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

//organize files into packages
class HomeFragment : Fragment(R.layout.fragment_home) {

    //organize files into packages
    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counterRv.layoutManager = GridLayoutManager(activity, 2)//do RV setup in one place
        observeData()
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
    }


    private fun observeData() {//ONLY VIEW SHOULD KNOW ABOUT CONTEXT - there are exceptions!
        viewModel.getCounters().observe(viewLifecycleOwner) { counters -> //this should be a new method

            if (counterRv.adapter == null/*this should be a new method*/) {
                //this should be a new method
                //init adapter
                Log.d("DEBUG_TAG", "counterRv Adapter not set, setting rn")
                counterRv.adapter = getAdapter(counters)

            } else {

                //notify adapter about changes
                counterRv.adapter?.notifyItemRangeChanged(0, counters.size)
            }
//too many empty lines
        }


    }


    private fun getAdapter(counters: MutableList<Counter>): GenericAdapter<Counter> {
        return GenericAdapter.Builder<Counter>()
            .setData(counters)
            .setLayoutResId(R.layout.counter_item)//shouldn't this be on viewHolder?
            .setViewHolder(getViewHolder())
            .build()
    }


    private fun getViewHolder(): CounterVH {
        return CounterVH.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToSecondFirstFragment)
            .build()
    }

// this should be in vm - by navigation service
    private fun navigateToSecondFirstFragment(item: Counter): Boolean {
        Log.d("DEBUG_TAG", "Going to second first fragment")

        //bundle - this should be a method
        val bundle = Bundle()
        bundle.putString("clicks_count", item.numberOfClicks.toString())

    //HomeWhateverRepo holds this data temporary

        //nav argument
//        val navArgument1= NavArgument.Builder().setDefaultValue("1").build()
//        view?.findNavController()?.graph?.addArgument("key_1", navArgument1)

        //pass bundle - this should be in vm - by navigation service
        view?.findNavController()?.navigate(R.id.secondFirstFragment, bundle)

        return true
    }
}
