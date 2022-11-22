package com.valentin.advancedcounter.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.adapter.GenericAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        getClicksAmountFromSharedPref()    //what shared prefs? - you don't need them
    }

    private fun observeData() {
        viewModel.getCounters().observe(viewLifecycleOwner) { counters -> //this should be a new method - oops

            if (isAdapterNotAttached())
                initAdapter(counters)
            else
                notifyAdapterAboutChanges(counters)
        }
    }

    private fun initAdapter(counters: MutableList<Counter>) {
        Log.d("DEBUG_TAG", "counterRv Adapter not set, setting rn")

        //setting layout manager for rv         //this kind of comments are irrelevant
        counterRv.layoutManager = GridLayoutManager(activity, 2)

        //setting adapter for rv            //this kind of comments are irrelevant
        counterRv.adapter = getAdapter(counters)
    }

    private fun notifyAdapterAboutChanges(counters: MutableList<Counter>) {
        Log.d("DEBUG_TAG", "notify adapter about changes")

        counterRv.adapter?.notifyItemRangeChanged(0, counters.size)
    }

    private fun isAdapterNotAttached(): Boolean {
        return counterRv.adapter == null
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
    }

    private fun getAdapter(counters: MutableList<Counter>): GenericAdapter<Counter> {
        return GenericAdapter.Builder<Counter>()
            .setData(counters)
            .setViewHolder(getViewHolder())
            .build()
    }

    private fun getViewHolder(): CounterVH {
        return CounterVH.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToSecondFirstFragment)
            .setLayoutResId(R.layout.counter_item)
            .build()
    }

    private fun navigateToSecondFirstFragment(item: Counter): Boolean {//unused parameter - either remove bcs it's not needed or fix issue
        viewModel.navigateToSecondFirstFragment(requireActivity())

        return true
    }

    private fun getClicksAmountFromSharedPref() {
        val sharedPref = activity?.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

        Log.d("DEBUG_TAG", sharedPref?.getString("clicks_amount", (0).toString()).toString())
    }
}
